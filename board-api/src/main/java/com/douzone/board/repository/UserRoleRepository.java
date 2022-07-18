package com.douzone.board.repository;

import com.douzone.board.entity.User;
import com.douzone.board.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
