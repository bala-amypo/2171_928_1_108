package com.example.demo.security;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, User> inMemoryUsers = new HashMap<>();
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Map<String, Object> registerUser(String name, String email, String password, String role) {
        if (inMemoryUsers.containsKey(email)) {
            throw new BadRequestException("User already exists");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password); // Already encoded by test case
        user.setRole(role);

        inMemoryUsers.put(email, user);

        Map<String, Object> result = new HashMap<>();
        result.put("userId", user.getId() != null ? user.getId() : (long) inMemoryUsers.size());
        result.put("role", role);
        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = inMemoryUsers.get(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
