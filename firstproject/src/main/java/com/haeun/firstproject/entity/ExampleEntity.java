package com.haeun.firstproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.criterion.Example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//엔터티 클래스는 @AllArgsConstructor와 getter 메서드만 존재해야한다고 개발자들과의 약속이라고 함
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Example")
@Table(name = "Example") // @Table 어노테이션을 이용하면 Entity와 Table을 명시적으로 지정할 수 있다. name: 엔티티와 매핑할 데이터베이스 테이블의 이름을 지정합니다.
public class ExampleEntity {
    //자바 네이밍 규칙 카멜 케이스로 명명을 하면 데이터베이스에서 해당 그 테이블을 찾지 못하게 됩니다.
    //컬럼이든 테이블이든 주의할 점 : 자바의 네이밍 규칙이 카멜 케이스인데 컬럼명을( @Column name속성에 그렇게 지정을 하게 되면 ) 카멜 케이스로 지정을 하면 exampleColumn 데이터베이스에서 해당 이름을 인식하지 못함
    //데이터베이스의 네이밍 규칙이 단어의 띄어 쓰기는 _기호로 표현을 함
    //자바 Entity클래스에서 변수의 이름을 지을 때 데이터베이스의 명명 규칙을 따라야함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "example_column1", nullable = false, unique = true) //@Column name속성에 지정한 이름이 실제 테이블의 컬럼명이 되므로 밑에 변수 선언의 명은 아무런 이름을 사용해도 상관 없는 것
    private int pk; //이런식으로 지정을 하면 Repository클래스에서 사용자가 쿼리 메서드를 만들 때 이 변수명을 적어야 하므로 헷갈리기 때문에 데이터베이스 컬럼명과 같게 선언하는 것이 좋다.
    private String exampleColumn2;
    private boolean exampleColumn3;
}
