package com.softarexpractice.logotype.repository;

import com.softarexpractice.logotype.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
