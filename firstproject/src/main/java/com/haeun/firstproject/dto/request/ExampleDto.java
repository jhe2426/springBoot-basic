package com.haeun.firstproject.dto.request;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class ExampleDto {
    //@NotNull 어노테이션을 선언해줬으면 적용도 꼭 시켜줘야함(컨트롤러에서 @Valid어노테이션을 추가해줘야 함)
    //@NotNull//얘를 사용하려면 build.gradle파일에 Validation라이브러리를 추가해줘야 함 @NotNull은 빈 문자열은 받아짐(빈 문자열또한 받지 않게 하려면 @NotEmpty사용하면 됨) 
    //@NotEmpty는 띄어쓰기로만 구성 되어있는 값은 값으로 인식을 하여 오류를 뱉지 않음
    @NotBlank //는 띄어쓰기로만 구성 되어있는 값은 값으로 인식하지 않아 오류를 뱉어냄
    private String parameter1;
    @Length(min = 0, max = 20)
    private String parameter2;
    private String parameter3;
}
