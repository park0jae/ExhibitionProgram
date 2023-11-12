package com.zerozae.exhibition.global.advisor;

import com.zerozae.exhibition.domain.exhibition.exception.DuplicateExhibitionException;
import com.zerozae.exhibition.domain.exhibition.exception.ExhibitionNotFoundException;
import com.zerozae.exhibition.domain.file.exception.ImageNotFoundException;
import com.zerozae.exhibition.domain.file.exception.NoExtException;
import com.zerozae.exhibition.domain.file.exception.UnSupportExtException;
import com.zerozae.exhibition.domain.member.exception.DuplicateMemberException;
import com.zerozae.exhibition.domain.member.exception.MemberNotFoundException;
import com.zerozae.exhibition.domain.reservation.exception.ImpossibleReservationException;
import com.zerozae.exhibition.domain.reservation.exception.ReservationNotFoundException;
import com.zerozae.exhibition.global.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    protected ErrorResponse exceptionMessage(Exception e){
        log.error("Error Message ={}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR.name(), e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    protected ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST.name(), e.getMessage());
        log.warn("Error Message = {}", errorMessage);
        return errorResponse;
    }

    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    protected ErrorResponse memberNotFoundException(MemberNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND.name(), "회원을 찾을 수 없습니다.");
        log.warn("Error Message = {}", errorResponse.message());
        return errorResponse;
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    protected ErrorResponse reservationNotFoundException(ReservationNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND.name() , "예약을 찾을 수 없습니다.");
        log.warn("Error Message = {}", errorResponse.message());
        return errorResponse;
    }

    @ExceptionHandler(ExhibitionNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    protected ErrorResponse exhibitionNotFoundException(ExhibitionNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND.name(), "전시회를 찾을 수 없습니다.");
        log.warn("Error Message = {}", errorResponse.message());
        return errorResponse;
    }

    @ExceptionHandler(DuplicateMemberException.class)
    @ResponseStatus(CONFLICT)
    protected ErrorResponse duplicateMemberException(DuplicateMemberException e) {
        ErrorResponse errorResponse = new ErrorResponse(CONFLICT.name(), "이미 존재하는 회원입니다.");
        log.warn("Error Message = {}", errorResponse.message());
        return errorResponse;
    }

    @ExceptionHandler(DuplicateExhibitionException.class)
    @ResponseStatus(CONFLICT)
    protected ErrorResponse duplicateExhibitionException(DuplicateExhibitionException e) {
        ErrorResponse errorResponse = new ErrorResponse(CONFLICT.name(), "이미 존재하는 전시회입니다.");
        log.warn("Error Message = {}", errorResponse.message());
        return errorResponse;
    }

    @ExceptionHandler(ImpossibleReservationException.class)
    @ResponseStatus(BAD_REQUEST)
    protected ErrorResponse ImpossibleReservationException(ImpossibleReservationException e) {
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST.name(), "예약이 불가능한 시간대입니다.");
        log.warn("Error Message = {}", errorResponse.message());
        return errorResponse;
    }

    @ExceptionHandler(UnSupportExtException.class)
    @ResponseStatus(UNSUPPORTED_MEDIA_TYPE)
    protected ErrorResponse unSupportExtException(UnSupportExtException e) {
        ErrorResponse errorResponse = new ErrorResponse(UNSUPPORTED_MEDIA_TYPE.name(), "지원하지 않는 미디어 타입입니다.");
        log.warn("Error Message = {}", errorResponse.message());
        return errorResponse;
    }

    @ExceptionHandler(NoExtException.class)
    @ResponseStatus(BAD_REQUEST)
    protected ErrorResponse noExtException(NoExtException e) {
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST.name(), "업로드한 파일의 확장자를 찾을 수 없습니다.");
        log.warn("Error Message = {}", errorResponse.message());
        return errorResponse;
    }

    @ExceptionHandler(ImageNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    protected ErrorResponse imageNotFoundException(ImageNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(NOT_FOUND.name(), "이미지를 찾을 수 없습니다.");
        log.warn("Error Message = {}", errorResponse.message());
        return errorResponse;
    }
}
