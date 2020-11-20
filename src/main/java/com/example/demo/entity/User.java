package com.example.demo.entity;

import java.util.Objects;

/**
 * description:
 *
 * @author wqh
 * @date 2020/11/20
 */
public class User {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(num, user.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }

    @Override
    public String toString() {
        return "User{" +
                "num=" + num +
                '}';
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    private Long num;


    public User(Long num) {
        this.num = num;
    }
    public User() {
    }
}
