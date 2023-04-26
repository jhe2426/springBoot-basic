package com.haeun.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haeun.board.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    
}
