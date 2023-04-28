package com.haeun.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haeun.board.entity.CommentEntity;

@Repository
public interface CommentRepository  extends JpaRepository<CommentEntity, Integer>{
    
    List<CommentEntity> findByBoardNumber(int boardNumber);
    
}