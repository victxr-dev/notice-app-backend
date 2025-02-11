package com.noticias.notice.service;

import com.noticias.notice.entity.NoticeEntity;
import com.noticias.notice.exception.CustomException;
import com.noticias.notice.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public List<NoticeEntity> findAllNotice(){
        return noticeRepository.findAll();
    }

    public NoticeEntity findByIdNotice(int id){
        return noticeRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND,"notice not found"));
    }

    public List<NoticeEntity> recommendedNotice(int n){
        List<NoticeEntity> allRecommendedNotice = noticeRepository.findByRecommendedTrue();
        Collections.shuffle(allRecommendedNotice);
        return allRecommendedNotice.stream().limit(n).collect(Collectors.toList());
    }
}
