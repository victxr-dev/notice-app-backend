package com.noticias.notice.exception;


import com.noticias.notice.dto.exception.MessageDto;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestControllerException {

    public final static Logger logger = LoggerFactory.getLogger(RestControllerException.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageDto> handleException(Exception e) {
        return ResponseEntity.internalServerError()
                .body(new MessageDto(e.getMessage()));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<MessageDto> handleCustomException(CustomException e) {
        return ResponseEntity.status(e.getStatus())
                .body(new MessageDto(e.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MessageDto> handleBadCredentialsException(BadCredentialsException e) {
        logger.info("PROBANDO BAD CREDENETIALS");
        logger.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageDto(e.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MessageDto> handleAccessDeniedException(AccessDeniedException e) {
        logger.info("PROBANDO ACCESO DENEGADO");
        logger.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new MessageDto(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> messages = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(err -> messages.add(err.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageDto(messages.stream().collect(Collectors.joining("," ))));
    }
    @ExceptionHandler(value = {MalformedJwtException.class, UnsupportedJwtException.class, IllegalArgumentException.class, SignatureException.class})
    public ResponseEntity<MessageDto> jwtException(JwtException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new MessageDto(e.getMessage()));
    }
}