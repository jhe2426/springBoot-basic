package com.haeun.firstproject.service;

import com.haeun.firstproject.provider.UserRole;

public interface MainService {
    public String hello();
    public String getJwt(String data);
    public UserRole validJwt(String jwt);
}
