package com.haeun.firstproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.haeun.firstproject.service.MainService;

@RestController
public class MainController {
    

    private final MainService mainService;

    @Autowired //* Spring context에 등록한 Spring Bean을 IoC를 통해 DI를 수행하는 어노테이션이다.
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/hello")
    public String hello() {
        return mainService.hello();
    }

    @GetMapping("/jwt/{data}")
    public String getJwt(@PathVariable("data") String data) {
        return mainService.getJwt(data);
    }
}
