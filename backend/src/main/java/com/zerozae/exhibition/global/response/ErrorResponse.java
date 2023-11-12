package com.zerozae.exhibition.global.response;

import lombok.Builder;


@Builder
public record ErrorResponse(

        String code,
        String message) {
}
