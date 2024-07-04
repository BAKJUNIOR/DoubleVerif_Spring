package com.example.doubleverif_spring.controller;

import com.example.doubleverif_spring.service.TwoFAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/2fa")
public class TwoFAController {

    @Autowired
    private TwoFAService twoFAService;

    @PostMapping("/sendCode")
    public String sendCode(@RequestParam String contact, @RequestParam String method) {
        return twoFAService.sendCode(contact, method);
    }

    @PostMapping("/validateCode")
    public boolean validateCode(@RequestParam String contact, @RequestParam String code){
        return twoFAService.validateCode(contact, code);
    }
}
