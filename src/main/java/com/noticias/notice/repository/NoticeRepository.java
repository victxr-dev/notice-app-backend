package com.noticias.notice.repository;

import com.noticias.notice.entity.NoticeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity,Integer> {
    List<NoticeEntity> findByRecommendedTrue();
}
