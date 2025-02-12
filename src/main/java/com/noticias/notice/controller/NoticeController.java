package com.noticias.notice.controller;

import com.noticias.notice.dto.notice.CategoryDto;
import com.noticias.notice.entity.NoticeEntity;
import com.noticias.notice.service.NoticeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
@CrossOrigin("*")
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

    @GetMapping("/category/{category}")
    public ResponseEntity<List<NoticeEntity>> category(@PathVariable("category") String category){
        return ResponseEntity.ok(noticeService.findCategory(category));
    }

}
