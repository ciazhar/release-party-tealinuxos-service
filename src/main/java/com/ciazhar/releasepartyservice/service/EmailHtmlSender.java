package com.ciazhar.releasepartyservice.service;

import com.ciazhar.releasepartyservice.model.EmailStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


/**
 * Created by ciazhar on 01/12/17.
 * [ Documentatiion Here ]
 */

@Component
public class EmailHtmlSender{
    @Autowired private EmailSender mailSender;

    @Autowired private TemplateEngine templateEngine;

    public EmailStatus send(String to, String subject, String templateName, Context context) {
        String body = templateEngine.process(templateName, context);
        return mailSender.sendHtml(to, subject, body);
    }
}