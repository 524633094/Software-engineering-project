package com.nwnu.greencloud.api;

import com.nwnu.greencloud.domain.UserEntity;
import com.nwnu.greencloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class User {
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/user")
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }
}
