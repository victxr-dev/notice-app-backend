package com.noticias.notice.repository;

import com.noticias.notice.entity.RoleEntity;
import com.noticias.notice.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    Optional<RoleEntity> findByRole(RoleEnum role);
}
