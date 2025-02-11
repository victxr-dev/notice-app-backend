package com.noticias.notice.service;

import com.noticias.notice.exception.CustomException;
import com.noticias.notice.entity.UserEntity;
import com.noticias.notice.entity.UserPrincipal;
import com.noticias.notice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND,"user not found"));
        return UserPrincipal.build(user);
    }
}
