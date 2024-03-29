package com.springboot.springboot.controller;

import com.springboot.springboot.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("usuario", new Usuario());
        modelAndView.addObject("msg", new ArrayList<>());

        return modelAndView;
    }
//
//    @RequestMapping("/")
//    public String index(){return "index";}
}
