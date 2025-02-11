package com.noticias.notice.service;

import com.noticias.notice.entity.RoleEntity;
import com.noticias.notice.enums.RoleEnum;
import com.noticias.notice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Optional<RoleEntity> findByRoleName(RoleEnum roleEnum){
        return roleRepository.findByRole(roleEnum);
    }
    public void saveRole(RoleEntity role){
        this.roleRepository.save(role);
    }
}
