package com.haeun.board.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.haeun.board.provider.JwtProvider;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtProvider jwtProvider;

    @Autowired
    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                try {
                    
                    String jwt = parseToken(request);

                    boolean hasJwt = jwt != null;
                    if (!hasJwt) {
                        filterChain.doFilter(request, response);
                        return;
                    }

                    //로그인한 사용자의 이메일 가져오는 코드
                    String email = jwtProvider.validate(jwt);

                    AbstractAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.NO_AUTHORITIES);
                    
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    securityContext.setAuthentication(authenticationToken);
                    SecurityContextHolder.setContext(securityContext);
                } catch (Exception exception) {
                   exception.printStackTrace();
                }

                //request, response의 값을 넘김
                filterChain.doFilter(request, response);

    }
    
    
    //토큰 파싱
    //파싱: 어떤 큰 자료에서 원하는 정보만 가공하고 뽑아서 원하는 때에 불러올 수 있게 하는 것
    private String parseToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        boolean hasToken = token != null && !token.equalsIgnoreCase("null");

        if (!hasToken) return null;

        boolean isBearer = token.startsWith("Bearer ");
        if (!isBearer) return null;

        String jwt = token.substring(7);

        return jwt;
    }

}
