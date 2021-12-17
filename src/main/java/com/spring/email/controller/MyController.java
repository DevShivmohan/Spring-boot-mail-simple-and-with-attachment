package com.spring.email.controller;

import com.spring.email.entity.SendEmailEntity;
import com.spring.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/send")
public class MyController {
    @Autowired
    private EmailService emailService;

    /**
     * send email controller
     * @param sendEmailEntity
     * @return
     */
    @RequestMapping(value = "/simple",method = RequestMethod.POST)
    public ResponseEntity<?> sendMail(@RequestBody SendEmailEntity sendEmailEntity){
        return emailService.sendMail(sendEmailEntity);
    }

    @RequestMapping(value = "/attachment",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> sendMailAttachment(@RequestParam("email") String email, @RequestParam("file")MultipartFile multipartFile){
        SendEmailEntity sendEmailEntity=new SendEmailEntity();
        sendEmailEntity.setEmail(email);
        sendEmailEntity.setMultipartFile(multipartFile);
        return emailService.sendMailWithAttachment(sendEmailEntity);
    }
}
