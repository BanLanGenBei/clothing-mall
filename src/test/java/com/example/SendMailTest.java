package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/2/4 14:18
 */
@SpringBootTest
public class SendMailTest {
    @Autowired
    JavaMailSenderImpl mailSender;
    @Test
    public void testSendMail(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("这是一个测试");
        simpleMailMessage.setText("我是陈冲");
        simpleMailMessage.setTo("18762084218@163.com");
        simpleMailMessage.setFrom("18762084218@163.com");

        mailSender.send(simpleMailMessage);
    }
}
