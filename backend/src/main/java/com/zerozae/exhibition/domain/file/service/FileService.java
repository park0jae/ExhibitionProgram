package com.zerozae.exhibition.domain.file.service;


import com.zerozae.exhibition.domain.file.entity.Image;
import com.zerozae.exhibition.domain.file.exception.FileUploadFailureException;
import com.zerozae.exhibition.domain.file.exception.ImageNotFoundException;
import com.zerozae.exhibition.domain.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {

    private String location = System.getProperty("user.dir") + "/images/";
    private final FileRepository fileRepository;

    @PostConstruct
    void postConstruct() { // 파일 업로드 될 디렉토리가 없을경우 생성
        File dir = new File(location);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public String getFilePath() {
        return location;
    }

    public void upload(MultipartFile file, String fileName) {
        try {
            file.transferTo(new File(location + fileName));
        } catch (IOException e) {
            throw new FileUploadFailureException();
        }
    }

    public void delete(String filename) {
        new File(location + filename).delete();
    }

    public Image findImageById(Long id) {
        return fileRepository.findById(id).orElseThrow(ImageNotFoundException::new);
    }
}
