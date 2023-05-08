package com.haeun.board.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.haeun.board.dto.request.board.PostBoardRequestDto;
import com.haeun.board.dto.request.board2.PostBoardRequestDto2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Board")
@Table(name = "Board")
public class BoardEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardNumber;
    private String writerEmail;
    private String title;
    private String content;
    private String boardImageUrl;
    private String writeDatetime;
    private int viewCount;

    public BoardEntity(PostBoardRequestDto dto) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);

        this.writerEmail = dto.getBoardWriterEmail();
        this.title = dto.getBoardTitle();
        this.content = dto.getBoardContent();
        this.boardImageUrl = dto.getBoardImageUrl();
        this.writeDatetime = writeDatetime;
        this.viewCount = 0; //데이터베이스에 디폴트 값으로 0으로 초기화 되어있으면 굳이 이렇게 넣어주지 않아도 된다고 함
    }

    public BoardEntity(String userEmail, PostBoardRequestDto2 dto) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);

        this.writerEmail = userEmail;
        this.title = dto.getBoardTitle();
        this.content = dto.getBoardContent();
        this.boardImageUrl = dto.getBoardImageUrl();
        this.writeDatetime = writeDatetime;
        this.viewCount = 0; //데이터베이스에 디폴트 값으로 0으로 초기화 되어있으면 굳이 이렇게 넣어주지 않아도 된다고 함
    }
}


