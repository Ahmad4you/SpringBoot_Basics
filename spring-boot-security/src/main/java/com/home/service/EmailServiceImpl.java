package com.home.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.home.model.EmailEntity;
import com.home.repository.EmailRepository;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author Ahmad Alrefai
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {

    private final static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private EmailRepository emailRepository;

    @Value("${email.host}")
    private String HOST;

    @Value("${email.port}")
    private int PORT;

    @Value("${email.from}")
    private String FROM;

    @Value("${email.password}")
    private String PASSWORD;

    @Override
    public void sendEmail(List<String> to, List<String> cc, String subject, String content) {
        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setTo(to);
        emailEntity.setCc(cc);
        emailEntity.setSubject(subject);
        emailEntity.setContent(content);
        emailEntity.setDate(new Date());

        emailRepository.save(emailEntity);

        logger.info("Email has been saved to the database successfully....");
    }
}