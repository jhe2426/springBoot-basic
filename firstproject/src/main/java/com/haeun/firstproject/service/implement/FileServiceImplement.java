package com.haeun.firstproject.service.implement;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.haeun.firstproject.service.FileService;

@Service
public class FileServiceImplement implements FileService {

    @Value("${file.path}")
    private String FILE_PATH; // 파일을 물리적으로 저장하기 위한 경로
    @Value("${file.url}")
    private String FILE_URL; // 사용자가 파일을 접근하기 위한 URL

    @Override
    public String upload(MultipartFile file) {
       
        // 빈 값인지 검증
        if (file.isEmpty()) return null;

        //* 파일명 가져오기
        String originalFileName = file.getOriginalFilename(); //원래 파일명 가져오기
        //* 확장자 가져오기
        int extensionIndex = originalFileName.lastIndexOf("."); // .중에 제일 마지막에 있는 인덱스를 가져오기 됨
        String extenstion = originalFileName.substring(extensionIndex);

        //* 파일의 새로운 이름 지정 : 다른 사용자들이 파일을 업로드 하는데 파일의 이름이 같을 수도 있기 때문에 같은 파일 이름은 저장 했을 시 중복이 안 되거나 
        //* 파일의 원래 이름에서(1),(2) 이런식으로 붙기 때문에 새롭게 파일 이름을 지정해주는 것
        String uuid = UUID.randomUUID().toString();
        String saveName = uuid + extenstion;

        //* 파일 저장 경로 지정
        String savePath = FILE_PATH + saveName;

        try {
            //* 물리적인 파일을 저장
            file.transferTo(new File(savePath));

        }catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        //* 클라이언트가 해당 파일에 접근하기 위한 url
        String fileUrl = FILE_URL + saveName;
        return fileUrl;
    }

    @Override
    public Resource getFile(String fileName) {
       
        Resource file = null;

        try {
            
            //* 파일을 URL로 가져오기
            String url = "file:" + FILE_PATH + fileName;
            file = new UrlResource(url);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return file;

    }
    
}
