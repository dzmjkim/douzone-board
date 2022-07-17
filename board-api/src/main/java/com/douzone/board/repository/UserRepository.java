package com.douzone.board.repository;

import com.douzone.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository{
    User findByUsername(String username);
}
