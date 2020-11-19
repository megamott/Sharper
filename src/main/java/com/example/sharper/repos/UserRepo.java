package com.example.sharper.repos;

import com.example.sharper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Matvey on Nov, 2020
 */
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
