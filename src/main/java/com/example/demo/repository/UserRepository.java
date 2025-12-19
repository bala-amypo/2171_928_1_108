package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional; // ✅ Add this import

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Example method using Optional
    Optional<User> findByEmail(String email);
}
