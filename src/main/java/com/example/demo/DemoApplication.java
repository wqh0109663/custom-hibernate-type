package com.example.demo;

import com.example.demo.entity.MyUser;
import com.example.demo.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }



    @Autowired
    private MyUserRepository myUserRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println();
      /*  MyUser myUser = new MyUser();
//        myUser.setId(1L);
        myUser.setUserId(new User(123L));
        myUserRepository.saveAndFlush(myUser);*/
        List<MyUser> all =
                myUserRepository.findAll();
        System.out.println(all);

    }
}
