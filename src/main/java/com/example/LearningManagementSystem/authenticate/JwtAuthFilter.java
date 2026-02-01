package com.example.LearningManagementSystem.authenticate;

import com.example.LearningManagementSystem.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final AuthService authService;
    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Cookie[] cookies =request.getCookies();

        if(cookies==null){
            filterChain.doFilter(request,response);
            return;
        }

        String authToken=null;

        for (Cookie cookie:cookies){
            if(cookie.getName().equals("accessToken")){
                authToken=cookie.getValue();
            }
        }

        if(authToken==null){
            String header=request.getHeader("Authorization");
            if(header!=null && header.startsWith("Bearer ")){
                authToken=header.substring(7);
            }
        }

        if(authToken==null){
            filterChain.doFilter(request,response);
            return;
        }

        String username=jwtUtil.extractUserName(authToken);

        if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=authService.loadUserByUsername(username);

            if(jwtUtil.isTokenValid(authToken,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request,response);
    }
}

