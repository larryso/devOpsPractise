package com.example.demo.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Slf4j
public class RequestLoggingFilter extends CommonsRequestLoggingFilter {
    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        Calendar calendar = GregorianCalendar.getInstance();
        Date dateTime = calendar.getTime();
        logger.info(message);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        super.doFilterInternal(request, response, filterChain);
    }
}