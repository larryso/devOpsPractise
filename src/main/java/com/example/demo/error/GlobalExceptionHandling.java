package com.example.demo.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    protected ModelAndView handleGloabalException(Exception e, WebRequest request){
        return new ModelAndView("error");
    }
}
