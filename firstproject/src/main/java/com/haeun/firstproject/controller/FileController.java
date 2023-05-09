package com.haeun.firstproject.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.haeun.firstproject.common.constant.RequestPattern;
import com.haeun.firstproject.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(RequestPattern.FILE_API) //RequestPattern.FILE_API 이렇게 작성하는 것이 관리하기에 편함
@RequiredArgsConstructor
public class FileController {
     
    private final FileService fileService;

    private final String UPLOAD_URL = "upload";
    private final String GET_URL = "{fileName}";

    //* 파일 업로드(이미지) 파일을 넘겨 받을 떄 MultipartFile 타입으로 받게 됨 POST MAN에서 form-data로 전송하면 됨
    @PostMapping(UPLOAD_URL)
    public String upload(
        @RequestParam("file") MultipartFile file // Get, Delete Method에서 요청시 데이터를 전송받는 방식이다. path에 데이터를 추가하여 받아오는 방법이다. file POST방식에도 사용이 된다.
    ) {
        return fileService.upload(file);  //* file.getOriginalFilename(); getOriginalFilename 넘겨 받은 파일의 이름임
    }

    //* 파일 불러오기
    @GetMapping(value = GET_URL, produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE}) //produces : 출력하고자 하는 데이터 포맷을 정의한다.
    public Resource getFile(
    @PathVariable("fileName") String fileName
    ) {
        return fileService.getFile(fileName);
    }
}
