package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User createUser(User user) {
        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("Test User");
        return user;
    }
}
