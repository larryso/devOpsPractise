package com.example.demo.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexControler {
    @GetMapping(value = "/home")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
}
