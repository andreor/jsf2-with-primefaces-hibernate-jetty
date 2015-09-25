package com.g2app.github.persistence;

import com.g2app.github.model.User;

import java.util.List;

public interface UserHibernate {

    User getUser(int id);
    boolean insertUser(User User);
    boolean deleteUser(int User);

    List<User> findAllUsers();
    List<User> usersFilter(User filter);

    boolean updateUser(User user);

}
