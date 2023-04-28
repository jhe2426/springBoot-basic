package com.haeun.board.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.haeun.board.dto.response.ResponseDto;
import com.haeun.board.entity.BoardEntity;
import com.haeun.board.entity.CommentEntity;
import com.haeun.board.entity.LikyEntity;
import com.haeun.board.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBoardResponseDto  extends ResponseDto{ //모든 ResposeDto에 code와 message가 존재하므로 상속을 받은 것
     private int boardNumber;
     private String boardTitle;
     private String boardContent;
     private String boardImageUrl;
     private String boardWriteDatetime;
     private int viewCount;
     private String boardWriterEmail;
     private String boardWriterNickname;
     private String boardWriterProfileImageUrl;
     private List<Comment> commentList;
     private List<Liky> likeList;


     public GetBoardResponseDto( //*모든 레이아웃간의 데이터 교환을 Entity자체를 반환해주는 행위는 보안에 대해 실수로(비밀번호등 개인정보를) 노출 시킬 수도 있으므로 좋지 않는 방법임 그래서 DTO 클래스를 사용하는 것
        BoardEntity boardEntity, UserEntity userEntity,
         List<CommentEntity> commentEntities, List<LikyEntity> likyEntities
    ) {
            super("SU", "Success"); //ResponseDto를 상속 받고 있기 때문에 가장 먼저 초기화를 해줘야함(부모부터 항상 먼저 초기화 해줘야 함)
            
            this.boardNumber = boardEntity.getBoardNumber();
            this.boardTitle = boardEntity.getTitle();
            this.boardContent = boardEntity.getContent();
            this.boardImageUrl = boardEntity.getBoardImageUrl();
            this.boardWriteDatetime = boardEntity.getWriteDatetime();
            this.viewCount = boardEntity.getViewCount();
            this.boardWriterEmail = userEntity.getEmail();
            this.boardWriterNickname = userEntity.getNickname();
            this.boardWriterProfileImageUrl = userEntity.getProfileImageUrl();
            this.commentList = Comment.createList(commentEntities);
            this.likeList = Liky.createList(likyEntities);
     }
}


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Comment {
    private int commentNumber;
    private int boardNumber;
    private String commentWriterEmail;
    private String commentContent;
    private String commentWriternickname;
    private String commentWriterProfileImageUrl; 
    private String commentWriterDatetime;

    Comment(CommentEntity commentEntity) {
        this.commentNumber = commentEntity.getCommentNumber();
        this.boardNumber = commentEntity.getBoardNumber();
        this.commentWriterEmail = commentEntity.getUserEmail();
        this.commentContent = commentEntity.getCommentContent();
        this.commentWriternickname = commentEntity.getUserNickname();
        this.commentWriterProfileImageUrl = commentEntity.getUserProfileImageUrl(); 
        this.commentWriterDatetime = commentEntity.getWirteDatetime();
    }
    //* Entity클래스를 DTO클래스로 복사하는 행위 : 지금 이 작업을 한 이유는 서버에서 클라이언트로 반환을 해줘야하므로 데이터 이동 수단을 dto를 이용해야 하므로 entity에 있는 값을 dto에 넣고 있는 거
    static List<Comment> createList(List<CommentEntity> commentEntities)  { 
        List<Comment> commentList = new ArrayList<Comment>();
        for (CommentEntity commentEntity: commentEntities) { 
            Comment comment = new Comment(commentEntity);
            commentList.add(comment);
        }
        return commentList;
    }
    //데이터베이스를 설계할 때 유저 테이블과 조인을 해서 commentWriternickname, commentWriterProfileImageUrl해당 정보들을 가져오려고 했는데
    //JPA는 데이터베이스 무슨 작업을 하든 부하가 발생하므로, 조인을 네이티브 쿼리 메서드를 사용해서 조인을 해도 되지만 이것 또한 부하를 발생 시킴
    //그래서 해당 컬럼의 중복을 허용해서 해결하는 방법도 있다고 함
   //조인을 한 view를 엔터티로 사용을 하거나,  private String userEmail;필드들을 중복을 허용하면 된다고 함
   //조인을 한 view를 엔터티로 사용하는 경우는 : 하루에 못해도 10번씩 바뀌는 작업들이 이렇나는 경우에 사용하는 것이고
   // private String userEmail;필드들을 중복을 허용 하는 경우는 데이터 값이 잘 바뀌지 않을 때 사용하면 된다
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Liky { 
    private int boardNumber;
    private String userEmail;
    private String userNickname;
    private String userProfileImageUrl;

    Liky(LikyEntity likyEntity) {
       this.boardNumber = likyEntity.getBoardNumber();
       this.userEmail = likyEntity.getUserEmail();
       this.userNickname = likyEntity.getUserNickname();
       this.userProfileImageUrl = likyEntity.getUserProfileImageUrl();
    }

    static List<Liky> createList(List<LikyEntity> likyEntities) { //Entity클래스를 DTO클래스로 복사하는 행위
        List<Liky> likeList = new ArrayList<Liky>();
        for (LikyEntity LikyEntity: likyEntities) {
                Liky liky = new Liky(LikyEntity);
                likeList.add(liky);
        }
        return likeList;
    }

}
