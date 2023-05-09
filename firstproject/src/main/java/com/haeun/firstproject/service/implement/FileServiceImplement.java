package com.haeun.firstproject.service.implement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.haeun.firstproject.service.FileService;

@Service
public class FileServiceImplement implements FileService {

    @Value("${file.path}")
    private String FILE_PATH;
    @Value("${file.url}")
    private String FILE_URL;

    @Override
    public String upload(MultipartFile file) {
       
        // 빈 값인지 검증
        if (file.isEmpty()) return null;

        //* 파일명 가져오기
        String originalFileName = file.getOriginalFilename(); //원래 파일명 가져오기
        //* 확장자 가져오기
        int extensionIndex = originalFileName.lastIndexOf("."); // .중에 제일 마지막에 있는 인덱스를 가져오기 됨
        String extenstion = originalFileName.substring(extensionIndex);

        //* 파일의 새로운 이름 지정 : 다른 사용자들이 파일을 업로드 하는데 파일의 이름이 같을 수도 있기 때문에 같은 파일 이름은 저장 했을시 중복이 안 되므로 새롭게 파일 이름을 지정해주는 것

    }
    
}
