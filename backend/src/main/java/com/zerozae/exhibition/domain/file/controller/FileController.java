package com.zerozae.exhibition.domain.file.controller;

import com.zerozae.exhibition.domain.file.entity.Image;
import com.zerozae.exhibition.domain.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/api/v1/images/{imageId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long imageId) throws IOException {
        Image image = fileService.findImageById(imageId);
        String imageName = image.getUniqueName();
        String ext = image.getExt(image.getOriginName());

        Resource resource = new FileSystemResource(fileService.getFilePath() + imageName);
        byte[] imageBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());

        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("image/" + ext);
        headers.setContentType(mediaType);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
