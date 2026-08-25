package com.bank.bank_core.user.repository;

import com.bank.bank_core.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String Email);
    boolean existsByEmail(String Email);
}
