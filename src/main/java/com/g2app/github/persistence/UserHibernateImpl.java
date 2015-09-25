package com.g2app.github.persistence;

import com.g2app.github.model.User;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserHibernateImpl implements UserHibernate {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("db_users");
    private EntityManager em = factory.createEntityManager();


    public User getUser(int id) {
        try {
            User User = (User) em.find(com.g2app.github.model.User.class,id);
            return User;
        } catch (NoResultException e) {
            return null;
        }
    }


    public List<User> usersFilter(User filter) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<User> courseQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = courseQuery.from(User.class);

            List<Predicate> predicates = new ArrayList<Predicate>();

            if (filter != null) {
                if (filter.getName() != null && !"".equals(filter.getName())) {
                    Predicate namePredicate = criteriaBuilder.like(userRoot.<String>get("name"), filter.getName());
                    predicates.add(namePredicate);
                }
                if (filter.getUserName() != null && !"".equals(filter.getUserName())) {
                    Predicate namePredicate = criteriaBuilder.like(userRoot.<String>get("userName"), filter.getUserName());
                    predicates.add(namePredicate);
                }
            }
            courseQuery.where(predicates.toArray(new Predicate[] {}));
            TypedQuery<User> query = em.createQuery(courseQuery);
           return  query.getResultList();

        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.flush();

            em.getTransaction().commit();


            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertUser(User user){
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.flush();

            em.getTransaction().commit();


            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int id) {
        try {
            em.getTransaction().begin();
            em.remove(getUser(id));
            em.flush();

            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<User> findAllUsers() {
        try {
            Query query = em.createQuery("SELECT u FROM User u");
            return (List<User>) query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }


}
