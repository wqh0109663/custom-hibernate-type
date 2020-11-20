package com.example.demo.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * description:
 *
 * @author wqh
 * @date 2020/11/20
 */
@Entity
public class MyUser {

    @Override
    public String toString() {
        return "MyUser{" +
                "id=" + id +
                ", userId=" + userId +
                '}';
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public MyUser(Long id, User userId) {
        this.id = id;
        this.userId = userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;



    @Type(type = "com.example.demo.type.MyUserType")
    private User userId;

    public MyUser() {
    }

    public void setId(Long id) {
        this.id = id;
    }


}
