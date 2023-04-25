package com.haeun.firstproject.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haeun.firstproject.entity.ExampleEntity;

@Repository //@Component로 사용되긴 하지만 해당 클래스가 Repository를 담당한다는 것을 직접적으로 보여주기 위하여 Repository라는 이름을 사용한다.
//JPA를 사용하면 구현체를 개발자가 생성할 필요 없이 메서드 선언부의 이름을 규칙에 대해 잘 선언만 해주면 JPA가 구현체를 만들어 줌
public interface ExampleRepository extends JpaRepository<ExampleEntity, Integer>{ //<Entity클래스이름,PK타입>
    //* 컬럼명을 오타내면 RUN TIME 에러가 발생하게 됨(에러가 발생 됐을 때 at이 끝나는 위에 Caused by에 나오는 이유를 잘 읽어보면 오류의 이유를 알려줌)
    //쿼리 메서드 명이 findByPk인 이유는 ExampleEntity 클래스에서 필드명이 pk라고 변수명을 지정했으므로 그렇게 지정을 해줘야하는 것
    public ExampleEntity findByPk(int pk); // 반환 되는 계수는 0개에서 1개
    //* 메서드 선언 시 And를 사용할 시 순서에 맞게 변수의 타입을 선언해줘야함
    public List<ExampleEntity> findByExampleColumn3AndExampleColumn2(boolean exampleColumn3, String exampleColumn2); // 유니크한 값이 아닌 경우에는 반환 되는 계수가 0개 이상이므로 반환을 List형을 받는 것이다.

    public boolean existsByExampleColumn3(boolean exampleColumn3);
}


