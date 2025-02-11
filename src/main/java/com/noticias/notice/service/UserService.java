package com.noticias.notice.service;

import com.noticias.notice.dto.exception.MessageDto;
import com.noticias.notice.dto.jwt.JwtDto;
import com.noticias.notice.entity.RoleEntity;
import com.noticias.notice.enums.RoleEnum;
import com.noticias.notice.exception.CustomException;
import com.noticias.notice.dto.user.SingInDto;
import com.noticias.notice.dto.user.SingUpDto;
import com.noticias.notice.entity.UserEntity;
import com.noticias.notice.jwt.JwtAuthenticationProvider;
import com.noticias.notice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    public Optional<UserEntity> findByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public MessageDto singUp(SingUpDto dto){
        if(this.userRepository.existsByEmail(dto.getEmail())){
            new CustomException(HttpStatus.BAD_REQUEST,"user exists");
        }
        UserEntity user = new UserEntity();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleService.findByRoleName(RoleEnum.ROLE_USER).get());
        user.setRoles(roles);
        this.userRepository.save(user);
        return new MessageDto("created user");
    }

    public JwtDto singIn(SingInDto dto){
        logger.info("PRUEBA1 DE SERVICE ");
        logger.info(dto.getEmail());
        logger.info(dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtAuthenticationProvider.generateToken(authentication);
        return new JwtDto(jwtToken);
    }

}
