package com.example.doubleverif_spring.controller;

import com.example.doubleverif_spring.service.EmailService;
import com.example.doubleverif_spring.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/2fa")
public class TwoFAController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private EmailService emailService;

    private final Random random = new Random();
    private String currentCode;



    @PostMapping("/sendCode")
    public String sendCode(@RequestParam String contact, @RequestParam String method) {
        currentCode = String.format("%06d", random.nextInt(999999));
        if ("sms".equalsIgnoreCase(method)) {
            smsService.sendSms(contact, "Your verification code is: " + currentCode);
        } else if ("email".equalsIgnoreCase(method)) {
            emailService.sendEmail(contact, "Votre code de vérification", "Votre code de vérification est : " + currentCode);
        }
        return "Code sent";

    }

    @PostMapping("/validateCode")
    public boolean validateCode(@RequestParam String code){
        return currentCode != null && currentCode.equals(code);
    }



}
