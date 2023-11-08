package com.zerozae.exhibition.domain.exhibition.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zerozae.exhibition.domain.exhibition.entity.Exhibition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExhibitionResponse {

    private Long id;
    private String exhibitionName;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;
    private String location;
    private long price;
    private Long imageId;

    public static ExhibitionResponse toDto(Exhibition exhibition) {
        return new ExhibitionResponse(
                exhibition.getId(),
                exhibition.getExhibitionName(),
                exhibition.getDescription(),
                exhibition.getStartTime(),
                exhibition.getEndTime(),
                exhibition.getLocation(),
                exhibition.getPrice(),
                exhibition.getImage() != null ? exhibition.getImage().getId() : null
        );
    }
}
