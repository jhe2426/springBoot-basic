package com.haeun.firstproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haeun.firstproject.entity.ExampleEntity;

@Repository //@Component로 사용되긴 하지만 해당 클래스가 Repository를 담당한다는 것을 직접적으로 보여주기 위하여 Repository라는 이름을 사용한다.
//JPA를 사용하면 구현체를 개발자가 생성할 필요 없이 메서드 선언부의 이름을 규칙에 대해 잘 선언만 해주면 JPA가 구현체를 만들어 줌
public interface ExampleRepository extends JpaRepository<ExampleEntity, Integer>{ //<Entity클래스이름,PK타입>
    
}
