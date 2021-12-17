package com.waa.AmazonMini.controller;

import com.waa.AmazonMini.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @GetMapping
    public String sendDumpEmail(){
        emailService.sendDumpEmail();
        return "Dump Email sent to sinov11@yopmail.com";
    }
}
