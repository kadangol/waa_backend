package com.waa.AmazonMini.mail.config;

import com.waa.AmazonMini.mail.config.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    public MailService mailService;

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Mail email) throws InterruptedException {
        mailService.sendEmail(email);
        System.out.println("Finished sending the email: <" + email + ">");
    }

}
