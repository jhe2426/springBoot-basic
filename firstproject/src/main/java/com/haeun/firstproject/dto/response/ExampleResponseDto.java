package com.haeun.firstproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
// @Getter @Data어노테이션도 없고 ?얘가 없는게  왜 오류였을까?  이 클래스의 맴버 변수는 private이므로 Requestbody에 출력해주려면 당연히 값을 가져와야한다 그래서  getter가 필요한 것 
// @Setter
@Builder
//@ToString
@AllArgsConstructor //* 모든 멤버변수를 매개변수로 받는 생성자를 작성해주는 어노테이션
public class ExampleResponseDto {
    private String data1;
    private String data2;
    private String data3;
}
