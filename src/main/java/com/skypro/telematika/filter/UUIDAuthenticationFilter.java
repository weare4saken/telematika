package com.skypro.telematika.filter;

import com.skypro.telematika.model.Token;
import com.skypro.telematika.service.AuthenticationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class UUIDAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;

    public UUIDAuthenticationFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String uuid = request.getHeader("X-Auth-Token");
        if(StringUtils.hasText(uuid)) {
            Optional<Token> token =  authenticationService.findByUuid(uuid);
            if(token.isPresent()) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        token.get(),
                        null,
                        List.of(new SimpleGrantedAuthority("USER"))
                );

                SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
                emptyContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(emptyContext);
            }
        }
        filterChain.doFilter(request, response);
    }
}
