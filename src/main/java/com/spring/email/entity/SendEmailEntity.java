package com.spring.email.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailEntity {
    private String email;
    private String subject="Spring boot messaging";
    private String message="Simple spring boot email messaging";
    private MultipartFile multipartFile;
}
