package com.haeun.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.haeun.board.entity.CommentEntity;

@Repository
public interface CommentRepository  extends JpaRepository<CommentEntity, Integer>{
    
    List<CommentEntity> findByBoardNumber(int boardNumber);
    
    // 기본적으로 JpaRepository가 제공하는 메서드들은 모두 트랜잭션 처리가 되어있다.
    // 그렇지만, 우리가 만들어서 추가한 메서드들은 트랜잭션 처리가 안되어 있기 때문에, repository에 @Transactional을 붙여주거나, 각각 메서드에 @Transactional을 붙여줘야한다.
    // 삭제 작업은 위험한 작업이므로 트랜잭션이 걸려 있어야 삭제 작업을 하도록 하고 있다.
    // 삭제 작업을 하는 도중에 오류가 발생해 데이터 손실이 잃어나면 안 되므로 트랜잭션을 이용하여 다시 원래 상태로 되돌리기 위해서는 꼭 트랜잭션이 필요한 것이다.
    @Transactional // 삭제 작업은 중요하므로 개발자 이 어노테이션을 작성하지 않으면 서버에서 삭제 작업을 할 때 오류를 뱉어냄
    void deleteByBoardNumber(int boardNumber);
}
