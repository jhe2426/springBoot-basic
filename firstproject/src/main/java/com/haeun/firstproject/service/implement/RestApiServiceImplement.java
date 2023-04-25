package com.haeun.firstproject.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haeun.firstproject.entity.ExampleEntity;
import com.haeun.firstproject.repository.ExampleRepository;
import com.haeun.firstproject.service.RestApiService;

@Service //구현되는 클래스에 선언을 하면 됨
//@Service생략하면 아래와 같은 오류가 밠생
//Consider defining a bean of type 'com.haeun.firstproject.service.implement.RestApiServiceImplement' in your configuration. 
public class RestApiServiceImplement implements RestApiService {
    
    private ExampleRepository exampleRepository;

    @Autowired
    public RestApiServiceImplement(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    public String postMethod() {
        //* 데이터 삽입
        //* 1. Entity 인스턴스 (= 데이터베이스 테이블의 레코드) 생성
        ExampleEntity exampleEntity = 
            ExampleEntity.builder()
            .exampleColumn2("string1")
            .exampleColumn3(false)
            .build();
        //* 2. Repository를 거쳐서 Entity 인스턴스를 저장
        exampleRepository.save(exampleEntity);
        return null;
    }

    public String getMethod() {
        //* 데이터 조회
        //* 1. JpaRepository에 있는 findBy 메서드로 Entity 조회
        //* 예외가 발생할 수 있는 상황임(1.pk가 1인 값이 없을 수도 있으므로 2.WAS서버가 켜진 후 데이터베이스가 꺼져 있어도 WAS서버에 영향을 주지 않음 그래서 데이터베이스 서버가 꺼져 있을 수도 있음)
        //* 데이터 베이스와 연결된 작업을(처리해야한다면) 해야한다면 전부 다 예외를 처리를 해줘야 함
        //ExampleEntity exampleEntity = exampleRepository.findById(1).get(); //는 아이디가 없다면 여기에서 예외가 발생하고
        ExampleEntity exampleEntity = exampleRepository.findByPk(1); //는 아이디가 없다면 여기에서는 NULL값을 반환 해줌 그래서 이렇게 따로 만들어서 하는 것이 작업하기가 수월해서 주로 PK키를 가져오는 것은 개발자가 따로 선언해서 사용한다고 함
    
        return exampleEntity == null ? "null" : exampleEntity.toString();
    }

    public String patchMethod() {
        //* 데이터 수정 첫 번째 방식(PATCH Method 방식)
        //* 1. 특정 조건으로 Entity 조회
        ExampleEntity exampleEntity = exampleRepository.findById(1).get();
        //* 2. 데이터 수정
        exampleEntity.setExampleColumn2("string2");
        //* 3. Entity 인스턴스 저장
        exampleRepository.save(exampleEntity);

        //* 데이터 수정 두 번째 방식(PUT Method 방식)
        //* 1. Entity 인스턴스 생성(필수 값은 전부 다 값을 넣어줘야 하므로 PUT방식이 모든 컬럼의 값을 변경하는 것이므로 이 방식이 PUT방식인 것)
        ExampleEntity exampleEntity2 = new ExampleEntity(2, "string3", true);
        //* 2. Entity 인스턴스 저장
        exampleRepository.save(exampleEntity2);
        //* save()메서드는 pk을 기준으로 같은 pk가 존재하면 오류를 내뱉는 것이 아니라 데이터 값을 바꿔 줌
        return null;
    }

    public String deleteMethod() {
        //* 데이터 삭제
        //* 1. JpaRepository에 있는 deieteBy 메서드로 Entity 삭제
        exampleRepository.deleteById(1);
        return null;
    }

}
