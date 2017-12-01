package com.ciazhar.releasepartyservice.service.impl;

import com.ciazhar.releasepartyservice.model.EmailStatus;
import com.ciazhar.releasepartyservice.model.request.RegisterForm;
import com.ciazhar.releasepartyservice.service.EmailHtmlSender;
import com.ciazhar.releasepartyservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

/**
 * Created by ciazhar on 02/12/17.
 * [ Documentatiion Here ]
 */

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    public EmailHtmlSender emailHtmlSender;


    @Override
    public EmailStatus sendEmail(RegisterForm form) {
        Context context = new Context();
        context.setVariable("title", "Clorus Email Verification");
        context.setVariable("description", "To Verify your clorus account please click link below ");
        context.setVariable("email",form.getEmail());

        EmailStatus emailStatus = emailHtmlSender.send(form.getEmail(), "Clorus Email Verification", "confirmation", context);

        return emailStatus;
    }
}
