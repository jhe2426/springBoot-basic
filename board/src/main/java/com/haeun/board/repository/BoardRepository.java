package com.haeun.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haeun.board.entity.BoradEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoradEntity, Integer> {
    
}
