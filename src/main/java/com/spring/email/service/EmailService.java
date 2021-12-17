package com.spring.email.service;

import com.spring.email.entity.SendEmailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * send mail only simple message
     * @param sendEmailEntity
     * @return
     */
    public ResponseEntity<?> sendMail(SendEmailEntity sendEmailEntity){
            try{
                SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
                simpleMailMessage.setSubject("First Spring message");
                simpleMailMessage.setTo(sendEmailEntity.getEmail());
                simpleMailMessage.setText(sendEmailEntity.getMessage());
                javaMailSender.send(simpleMailMessage);
                return ResponseEntity.status(HttpStatus.OK).body("Email sent to "+sendEmailEntity.getEmail());
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
    }

    /**
     * send mail only attachment message
     * @param sendEmailEntity
     * @return
     */
    public ResponseEntity<?> sendMailWithAttachment(SendEmailEntity sendEmailEntity){
        if(sendEmailEntity.getMultipartFile().isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Please select file");
        else
            try{
                MimeMessage mimeMessage=javaMailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
                mimeMessageHelper.setTo(sendEmailEntity.getEmail());
                mimeMessageHelper.setSubject(sendEmailEntity.getSubject());
                mimeMessageHelper.setText(sendEmailEntity.getMessage());
                mimeMessageHelper.addAttachment(sendEmailEntity.getMultipartFile().getOriginalFilename(),sendEmailEntity.getMultipartFile());
                javaMailSender.send(mimeMessage);
                return ResponseEntity.status(HttpStatus.OK).body("Email attachment sent to "+sendEmailEntity.getEmail());
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
    }
}
