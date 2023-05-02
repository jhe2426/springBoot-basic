package com.haeun.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.haeun.board.entity.BoardEntity;
import com.haeun.board.entity.resultSet.BoardListResultSet;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    public BoardEntity findByBoardNumber(int boardNumber);

    
    @Query(
        // 띄어쓰기도 중요함!! 
        // 이 아래의 sql문은 BoardEntity로 받을 수 가 없음  BoardEntity에 없는 필드들이 있으므로
        // 그래서 entity 폴더에 SELECT문에 작성되어  결과로 나오는 변수들을 받을 수 있는 인터페이스를 하나 만들면 됨
        value=
        "SELECT " +
        "B.board_number AS boardNumber," +
        "B.title AS boardTitle," +
        "B.content AS boardContent," +
        "B.board_image_url AS boardImageUrl," +
        "B.write_datetime AS boardWriteDatetime," +
        "B.view_count AS viewCount," +
        "U.email AS boardWrtierEmail," +
        "U.nickname AS boardWriterNickname," +
        "U.profile_image_url AS boardWriterProfileImageUrl," +
        "count(DISTINCT C.comment_number) AS commentCount," +
        "count(DISTINCT L.user_email) AS likeCount " +
        "FROM Board B, Comment C, Liky L, User U " +
        "WHERE B.board_number = C.board_number " +
        "AND B.board_number = L.board_number " +
        "AND B.writer_email = U.email " +
        "GROUP BY B.board_number " + 
        "ORDER BY boardWriteDatetime DESC",
        nativeQuery=true //true를 해줘야 실제 쿼리문으로 SQL이 작동하게 됨
    )
    public List<BoardListResultSet> getList();

    @Query(
        value=
        "SELECT " +
        "B.board_number AS boardNumber," +
        "B.title AS boardTitle," +
        "B.content AS boardContent," +
        "B.board_image_url AS boardImageUrl," +
        "B.write_datetime AS boardWriteDatetime," +
        "B.view_count AS viewCount," +
        "U.email AS boardWrtierEmail," +
        "U.nickname AS boardWriterNickname," +
        "U.profile_image_url AS boardWriterProfileImageUrl," +
        "count(DISTINCT C.comment_number) AS commentCount," +
        "count(DISTINCT L.user_email) AS likeCount " +
        "FROM Board B, Comment C, Liky L, User U " +
        "WHERE B.board_number = C.board_number " +
        "AND B.board_number = L.board_number " +
        "AND B.writer_email = U.email " +
        "GROUP BY B.board_number " + 
        "ORDER BY likeCount DESC " +
        "LIMIT 3",
        nativeQuery=true //true를 해줘야 실제 쿼리문으로 SQL이 작동하게 됨
    )
    public List<BoardListResultSet> getTop3List();
}
