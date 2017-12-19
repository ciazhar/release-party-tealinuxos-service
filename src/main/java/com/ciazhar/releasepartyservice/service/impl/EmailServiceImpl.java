package com.ciazhar.releasepartyservice.service.impl;

import com.ciazhar.releasepartyservice.model.MailBodyContent;
import com.ciazhar.releasepartyservice.model.Participant;
import com.ciazhar.releasepartyservice.model.request.RegisterForm;
import com.ciazhar.releasepartyservice.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by ciazhar on 02/12/17.
 * [ Documentatiion Here ]
 */

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender sender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailServiceImpl(JavaMailSender sender, TemplateEngine templateEngine) {
        this.sender = sender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmail(Participant participant){

        Gson gson = new Gson();
        String jsonParticipant = gson.toJson(participant);

        String templateName = "email/myTemplate";

        Context context = new Context();
        context.setVariable("participant",jsonParticipant);
        context.setVariable("name",participant.getName());

        String body = templateEngine.process(templateName, context);

        MimeMessage mail = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setTo(participant.getEmail());
            helper.setSubject("Seminar & Release Party TeaLinuxOS");
            helper.setText(body, true);
            helper.setFrom("ciazhar.id@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        sender.send(mail);
    }
}
