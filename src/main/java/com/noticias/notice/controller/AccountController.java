package com.noticias.notice.controller;

import com.noticias.notice.dto.exception.MessageDto;
import com.noticias.notice.dto.jwt.JwtDto;
import com.noticias.notice.dto.user.SingInDto;
import com.noticias.notice.dto.user.SingUpDto;
import com.noticias.notice.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/account")
@Configuration("*")
public class AccountController {

    private final static Logger logger = (Logger) LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<MessageDto> singUp(@Valid @RequestBody SingUpDto dto){
        return ResponseEntity.ok(userService.singUp(dto));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtDto> singIn(@Valid @RequestBody SingInDto dto){
        logger.info("PRUEBA EN CONTROLLER");
        logger.info(dto.getEmail());
        logger.info(dto.getPassword());
        return ResponseEntity.ok(userService.singIn(dto));
    }
}
