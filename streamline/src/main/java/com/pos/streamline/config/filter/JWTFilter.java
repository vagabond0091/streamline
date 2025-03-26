package com.pos.streamline.config.filter;

import com.pos.streamline.service.JWTService;
import com.pos.streamline.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext applicationContext;
    /**
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        logger.info(" Received Authorization Header: " + authHeader); // Debug Log

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println(" No valid Authorization header found!");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        logger.info("Received Token: " + token);
        String username = jwtService.extractUsername(token);

        System.out.println(" Extracted Username: " + username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = applicationContext.getBean(UserService.class).loadUserByUsername(username);
            logger.info("Loaded UserDetails: " + userDetails.getUsername());

            if (jwtService.validateToken(token, userDetails)) {
                logger.info(" Token is valid, setting authentication.");
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                logger.info(" SecurityContext Authentication: "
                        + SecurityContextHolder.getContext().getAuthentication());
            } else {
                logger.info(" Token validation failed!");
            }
        }
        else{
            logger.info(" SecurityContext already has authentication!");

        }

        filterChain.doFilter(request, response);
    }
}
