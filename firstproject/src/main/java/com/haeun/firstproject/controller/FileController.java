package com.haeun.firstproject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file")
public class FileController {
     
    //* 파일 업로드(이미지) 파일을 넘겨 받을 떄 MultipartFile 타입으로 받게 됨 POST MAN에서 form-data로 전송하면 됨
    @PostMapping("upload")
    public String upload(
        @RequestParam("file") MultipartFile file
    ) {
        return file.getOriginalFilename(); //* getOriginalFilename 넘겨 받은 파일의 이름임
    }
}
