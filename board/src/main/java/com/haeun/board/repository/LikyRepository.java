package com.haeun.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.haeun.board.entity.LikyEntity;
import com.haeun.board.entity.primaryKey.LikyPk;

@Repository
public interface LikyRepository extends JpaRepository<LikyEntity, LikyPk> { //복합 키는 여기에 타입을 지정할 수 없어서 PK 타입을 만들어 줘야한다고 함
    
    List<LikyEntity> findByBoardNumber(int boardNumber);

    @Transactional // 삭제 작업은 중요하므로 개발자 이 어노테이션을 작성하지 않으면 서버에서 삭제 작업을 할 때 오류를 뱉어냄
    void deleteByBoardNumber(int boardNumber);
}
