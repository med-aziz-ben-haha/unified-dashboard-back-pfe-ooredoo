package com.ooredoo.controllers;

import com.ooredoo.entities.User;
import com.ooredoo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    // http://localhost:8089/ooredoo/user/user-movies
    @GetMapping("/user-movies")
    @ResponseBody
    public Collection<User> getAll() {
        return userService.getAll();
    }

    // http://localhost:8089/ooredoo/user/list-all
    @GetMapping("/list-all")
    @ResponseBody
    public Collection<User> getAllUsers() { return userService.getAllUsers(); }

}
