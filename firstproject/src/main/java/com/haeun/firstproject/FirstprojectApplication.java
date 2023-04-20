package com.haeun.firstproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 루트 페키지 또는 베이스 페키지
//@SpringBootApplication 이 어노테이션이 루트의 폴더 위치를 지정되어지는 것이고
//해당 루트의 폴더 위치의 하위에만 bean을 넣는 어노테이션을 사용해야지 읽을 수가 있게 된다.
//어노테이션을 사용하지 않을 때는 어느 폴더에 클래스를 생성하든 상관없다고 함
public class FirstprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstprojectApplication.class, args);
	}

}
