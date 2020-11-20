package com.example.demo.repository;

import com.example.demo.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * description:
 *
 * @author wqh
 * @date 2020/11/20
 */
public interface MyUserRepository extends JpaRepository<MyUser,Long> {

}
