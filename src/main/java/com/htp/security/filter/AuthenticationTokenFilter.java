package com.htp.security.filter;

import com.htp.security.ApplicationHeaders;
import com.htp.security.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RequiredArgsConstructor
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    private TokenUtils tokenUtils;

    private UserDetailsService userDetailsService;

    public AuthenticationTokenFilter(TokenUtils tokenUtils, @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.tokenUtils = tokenUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        String authToken = httpServletRequest.getHeader(ApplicationHeaders.AUTH_TOKEN);
        chain.doFilter(req, res);

        if (StringUtils.isNotBlank(authToken)) {

            String userNameFromToken = tokenUtils.getUsernameFromToken(authToken);

            if (StringUtils.isNotBlank(userNameFromToken) && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(userNameFromToken);

                if (tokenUtils.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }
        }

    }
}
