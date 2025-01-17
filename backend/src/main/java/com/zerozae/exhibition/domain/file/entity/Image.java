package com.zerozae.exhibition.domain.file.entity;

import com.zerozae.exhibition.domain.file.exception.NoExtException;
import com.zerozae.exhibition.domain.file.exception.UnSupportExtException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Arrays;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class Image {

    private static final String extension[] = {"jpg", "jpeg", "gif", "bmp", "png"};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(nullable = false)
    private String uniqueName;

    @Column(nullable = false)
    private String originName;

    public Image(String originName) {
        this.originName = originName;
        this.uniqueName = extractExtAndGenerateUniqueName(originName);
    }

    public Image(String originName, String uniqueName) {
        this.originName = originName;
        this.uniqueName = uniqueName;
    }

    private String extractExtAndGenerateUniqueName(String originName) {
        String ext = getExt(originName);
        return UUID.randomUUID() + "." + ext;
    }

    public String getExt(String originName) {
        try {
            String ext = originName.substring(originName.lastIndexOf(".") + 1);
            if (supportFormat(ext)) {
                return ext;
            } else {
                throw new UnSupportExtException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoExtException();
        } catch (UnSupportExtException e) {
            throw e;
        }
    }

    private boolean supportFormat(String ext) {
        return Arrays.stream(extension).anyMatch(e -> e.equalsIgnoreCase(ext));
    }

}
