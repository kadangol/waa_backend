package com.waa.AmazonMini.service;

import com.waa.AmazonMini.domain.OrderLine;
import com.waa.AmazonMini.mail.config.Mail;
import com.waa.AmazonMini.mail.config.service.MailService;
import com.waa.AmazonMini.service.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@EnableJms
@Transactional
public class EmailService implements IEmailService{

    @Autowired
    MailService mailService;
    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${spring.mail.username}")
    private String sendEmailFrom;


    @Override
    public void sendOrderLineStatusChangeEmail(OrderLine orderLine){

        Mail mail = new Mail();

        mail.setMailFrom(sendEmailFrom);
        mail.setMailTo(orderLine.getBuyer().getUser().getEmail());
        mail.setMailSubject(orderLine.getOrderStatus().toString());
        mail.setMailContent(orderLine.getEmailString());


        System.out.println("Sending an email message asynchronously using JMS message queue.");
        jmsTemplate.convertAndSend("mailbox", mail);
    }
    @Override
    public void sendDumpEmail(){

        Mail mail = new Mail();

        mail.setMailFrom(sendEmailFrom);
        mail.setMailTo("sinov11@yopmail.com");
        mail.setMailSubject("dump");
        mail.setMailContent("dump");

        System.out.println("Sending an email message asynchronously using JMS message queue.");
        jmsTemplate.convertAndSend("mailbox", mail);
    }

}
