package com.ciazhar.releasepartyservice.service;

import com.ciazhar.releasepartyservice.model.EmailStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * Created by ciazhar on 01/12/17.
 * [ Documentatiion Here ]
 */

@Component
public class EmailSender {

    @Autowired JavaMailSender javaMailSender;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public EmailStatus sendPlainText(String to, String subject, String text) {
        return sendM(to, subject, text, false);
    }

    public EmailStatus sendHtml(String to, String subject, String htmlBody) {
        return sendM(to, subject, htmlBody, true);
    }

    private EmailStatus sendM(String to, String subject, String text, Boolean isHtml) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, isHtml);
            javaMailSender.send(mail);
            logger.info("Send email '{}' to: {}", subject, to);
            return EmailStatus.builder()
                    .to(to)
                    .subject(subject)
                    .body(text)
                    .status(EmailStatus.SUCCESS)
                    .build();
        } catch (Exception e) {
            logger.error(String.format("Problem with sending email to: {}, error message: {}", to, e.getMessage()));
            return EmailStatus.builder()
                    .to(to)
                    .subject(subject)
                    .body(text)
                    .errorMessage(e.getMessage())
                    .build();
        }
    }
}

