package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final List<User> users = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public User createUser(User user) {
        user.setId(nextId++);
        users.add(user);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        return user.orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public void deleteUser(Long id) {
        users.removeIf(u -> u.getId().equals(id));
    }
}
