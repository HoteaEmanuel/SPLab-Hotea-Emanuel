package com.example.sp_lab.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Link in the servlet filter chain (Chain of Responsibility): logs every request before and after processing
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        long start = System.currentTimeMillis();

        log.info("Request started: {} {}", method, uri);
        try {
            chain.doFilter(request, response);
        } finally {
            log.info("Request finished: {} {} -> {} ({} ms)",
                    method, uri, response.getStatus(), System.currentTimeMillis() - start);
        }
    }
}