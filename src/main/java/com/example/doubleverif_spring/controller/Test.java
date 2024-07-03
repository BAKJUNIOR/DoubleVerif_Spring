package com.example.doubleverif_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Test {
    public String getString(){
        return "Bonjour j'arrive ";
    }


    @GetMapping("/list")
    public int getInt (){
        return 10;
    }
}
