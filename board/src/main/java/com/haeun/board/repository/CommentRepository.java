package com.haeun.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.haeun.board.entity.CommentEntity;

@Repository
public interface CommentRepository  extends JpaRepository<CommentEntity, Integer>{
    
    List<CommentEntity> findByBoardNumber(int boardNumber);
    
    // 삭제 작업은 위험한 작업이므로 이 어노테이션을 사용하지 않으면 삭제 작업을 하지 못하도록 되어있다.
    @Transactional // 삭제 작업은 중요하므로 개발자 이 어노테이션을 작성하지 않으면 서버에서 삭제 작업을 할 때 오류를 뱉어냄
    void deleteByBoardNumber(int boardNumber);
}
