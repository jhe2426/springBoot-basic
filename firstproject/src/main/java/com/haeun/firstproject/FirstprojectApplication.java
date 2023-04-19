package com.haeun.firstproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication 이 어노테이션이 루트의 폴더 위치를 지정되어지는 것이고
//해당 루트의 폴더 위치의 하위에만 bean을 넣는 어노테이션을 사용해야지 읽을 수가 있게 된다.
public class FirstprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstprojectApplication.class, args);
	}

}
