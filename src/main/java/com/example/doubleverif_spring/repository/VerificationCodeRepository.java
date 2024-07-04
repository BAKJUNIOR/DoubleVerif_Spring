package com.example.doubleverif_spring.repository;

import com.example.doubleverif_spring.entitie.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    VerificationCode findByContact(String contact);
}
