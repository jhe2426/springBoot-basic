package com.haeun.firstproject.filter;

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

import com.haeun.firstproject.provider.JwtTokenProvider;
// 필터 구간에서 security가 작동이 됨 필터의 위치는 Controller 이전에 위치함
// equalsIgnoreCase 대소문 구별 없이 비교하는 메서드

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                try {
                    
                    String jwt = parseToken(request); //우리가 만든 jwt를 끌고 온 다음

                    boolean hasJwt = jwt != null;
                    if (!hasJwt) { //jwt 토큰이 없을 수도 있기 때문에 한 번 더 검증을 해주는 거
                        filterChain.doFilter(request, response);
                        return;
                    }

                    //subject를 가져오는 코드 subject는 거의 유저의 id값이 들어오게 됨
                    String subject = jwtTokenProvider.validate(jwt);

                    // AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(subject, null, AuthorityUtils.NO_AUTHORITIES);
                    // 비밀번호 : null 권한 : AuthorityUtils.NO_AUTHORITIES 권한을 가지고 있지 않다
                    //세번째 매개변수는 권한을 지정해주는 거
                    AbstractAuthenticationToken authenticationToken = 
                        new UsernamePasswordAuthenticationToken(subject, null, AuthorityUtils.NO_AUTHORITIES);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    securityContext.setAuthentication(authenticationToken);

                    SecurityContextHolder.setContext(securityContext);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                filterChain.doFilter(request, response);
    }

     //* Request Header 중 "Authorization" : "Bearer eyJh...." Bearer를 뺀 데이터를 가져오겠다는 것 
    private String parseToken(HttpServletRequest request) {
        
        //* Request Header 중 "Authorization" : "Bearer eyJh...." Header의 Authorization에 있는 값을 가져오겠다는 것
        String token = request.getHeader("Authorization"); //Header의 Authorization에 있는 값을 가져오겠다는 것 Header에 Authorization이름을 가지는 것이 있음
        
        //token.equalsIgnoreCase("null");는 왜 필요할까?  null이 들어오는 방식이 여러 가지라서  Null, NULL, null이런식으로 들어올 수 있다고 함
        boolean hasToken = token != null && !token.equalsIgnoreCase("null");
        if (!hasToken) return null; //토큰이 없다면 null을 반환

        //받은 토큰이 Bearer로 지정되어있나
        //* "Bearer eyJh...." 띄어쓰기 또한 꼭 포함 시켜야함
        boolean isBearer = token.startsWith("Bearer ");
        if (!isBearer) return null; //토큰이 Bearer로 지정되어 있지 않다면 null을 반환하겠다.

        //* "Bearer eyJh...." 여기에서 7번부터 값을 가져오겠다는 것(실제 토큰만 가져오겠다는 것)
        String jwt = token.substring(7); //8번째 부터 값을 가져오겠다.
        return jwt;
    
    }

    
}
