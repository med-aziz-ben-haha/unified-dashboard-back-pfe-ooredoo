package com.ooredoo.services;

import com.ooredoo.entities.User;
import com.ooredoo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
     UserRepository userRepository;

    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    public Collection<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
