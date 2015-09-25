package com.g2app.github.model;


import java.util.Date;
import javax.persistence.*;


@Entity
@Table(name="User")
public class User {

    @Id
    @GeneratedValue
    @Column(name="id", nullable=false, unique=true)
    private int id;

    @Column(name="userName", nullable=false, unique=true)
    private String userName;

    @Column(name="name", nullable=false, unique=true)
    private String name;

    @Column(name="password", nullable=false, unique=false)
    private String password;

    @Column(name="lastAccess", unique=true)
    @Temporal(TemporalType.DATE)
    private Date lastAccess;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
