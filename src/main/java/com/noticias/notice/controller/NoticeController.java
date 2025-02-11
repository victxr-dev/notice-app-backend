package com.noticias.notice.controller;

import com.noticias.notice.entity.NoticeEntity;
import com.noticias.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/all")
    public ResponseEntity<List<NoticeEntity>> findAllNotice(){
        return ResponseEntity.ok(noticeService.findAllNotice());
    }
    @GetMapping("/{id}")
    public ResponseEntity<NoticeEntity> findByIdNotice(@PathVariable("id") int id){
        return ResponseEntity.ok(noticeService.findByIdNotice(id));
    }
    @GetMapping("/recommended/{number}")
    public ResponseEntity<List<NoticeEntity>> recommendedNotice(@PathVariable("number") int number){
        return ResponseEntity.ok(noticeService.recommendedNotice(number));
    }

}
