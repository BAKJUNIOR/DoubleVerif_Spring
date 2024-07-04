package com.example.doubleverif_spring.service;

import com.example.doubleverif_spring.entitie.VerificationCode;
import com.example.doubleverif_spring.repository.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TwoFAService {

    @Autowired
    private SmsService smsService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    private final Random random = new Random();

    public String sendCode(String contact, String method) {
        String code = String.format("%06d", random.nextInt(999999));

        VerificationCode existingCode = verificationCodeRepository.findByContact(contact);
        if (existingCode != null) {
            verificationCodeRepository.delete(existingCode);
        }

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setContact(contact);
        verificationCode.setCode(code);
        verificationCodeRepository.save(verificationCode);

        // Envoyer le code par SMS ou email
        if ("sms".equalsIgnoreCase(method)) {
            smsService.sendSms(contact, "Your verification code is: " + code);
        } else if ("email".equalsIgnoreCase(method)) {
            emailService.sendEmail(contact, "Votre code de vérification", "Votre code de vérification est : " + code);
        }

        return "Code sent";
    }

    public boolean validateCode(String contact, String code) {
        VerificationCode verificationCode = verificationCodeRepository.findByContact(contact);
        return verificationCode != null && verificationCode.getCode().equals(code);
    }
}
