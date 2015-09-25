package com.g2app.github.beans;

import com.g2app.github.model.User;
import com.g2app.github.persistence.UserHibernateImpl;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.Date;
import java.util.List;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static javax.faces.context.FacesContext.getCurrentInstance;

@ManagedBean(name = "UserMB")
@ViewScoped
public class UserMB {

    private UserHibernateImpl userHibernate = new UserHibernateImpl();
    private User user = new User();

    private List<User> users;


    @PostConstruct
    public void init() {
        users = userHibernate.findAllUsers();
    }


    public String search() {

        users = userHibernate.usersFilter(user);
        if (users == null || users.isEmpty()) {
            user = new User();
            getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO, "List is Empty!", "Search."));
            return "/index";
        } else {
            return "/index";
        }
    }

    public String insert() {


        user.setLastAccess(new Date());

        if (userHibernate.insertUser(user)) {
            user = null;
            users = userHibernate.usersFilter(user);

            getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO, "Insert User Success", "Insert."));
            return "/index";
        } else {
            getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "Insert User error!", "Insert"));
            return "/insert";
        }

    }

    public String setUpdate(User pUser) {
        user = pUser;
        return "/update";

    }

    public String update(User user) {


        try {
            userHibernate.updateUser(user);
            getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO, "Update User Success", "Update."));
        } catch (Exception e) {
            getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "Update User error!", "Insert"));
        }
        return "/update";

    }

    public String delete(int id) {


        try {
            userHibernate.deleteUser(id);
            getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO, "Delete User Success", "Delete."));
            users = userHibernate.findAllUsers();
        } catch (Exception e) {
            getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "Delete User error!", "Insert"));
        }
        return "/index";


    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
