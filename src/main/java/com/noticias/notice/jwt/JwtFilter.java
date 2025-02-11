package com.noticias.notice.jwt;


import com.noticias.notice.service.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    JwtAuthenticationProvider jwtProvider;

    @Autowired
    UserServiceImpl userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(req);
            logger.info("TOKEN");
            logger.info(token);
            if (token!= null  && jwtProvider.validateToken(token)){
                logger.info("NOT NULL");
                String username = jwtProvider.getUsernameFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            logger.info("NULL");

        } catch (Exception e) {
            logger.info("fail in method dofilter " + e.getMessage());
        }
        filterChain.doFilter(req,res);
    }
    public String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if (header != null  && header.startsWith("Bearer "))
            return header.replace("Bearer ","");
        return null;
    }
}