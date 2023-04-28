package com.haeun.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haeun.board.entity.LikyEntity;
import com.haeun.board.entity.primaryKey.LikyPk;

@Repository
public interface LikyRepository extends JpaRepository<LikyEntity, LikyPk> { //복합 키는 여기에 타입을 지정할 수 없어서 PK 타입을 만들어 줘야한다고 함
    
    List<LikyEntity> findByBoardNumber(int boardNumber);

}
