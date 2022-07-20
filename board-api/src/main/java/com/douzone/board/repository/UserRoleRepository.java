package com.douzone.board.repository;

import com.douzone.board.entity.User;
import com.douzone.board.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findUserRolesByUser_id(Long id);
}