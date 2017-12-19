package com.ciazhar.releasepartyservice.service.impl;

import com.ciazhar.releasepartyservice.model.MailBodyContent;
import com.ciazhar.releasepartyservice.model.request.RegisterForm;
import com.ciazhar.releasepartyservice.service.EmailService;
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
    public void sendEmail(RegisterForm form){

        String templateName = "email/myTemplate";

        Context context = new Context();
        context.setVariable("Content", create());
        context.setVariable("name",form.toString());

        String body = templateEngine.process(templateName, context);

        MimeMessage mail = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setTo(form.getEmail());
            helper.setSubject("Coba");
            helper.setText(body, true);
            helper.setFrom("ciazhar.id@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        sender.send(mail);
    }

    private MailBodyContent create() {
        MailBodyContent content = MailBodyContent.builder().build();
        content.setUsername("Ciazhar");
        List<String> technology = new ArrayList<>();
        technology.add("Spring-Boot");
        technology.add("Thymeleaf");
        technology.add("Template Engine");
        content.setTechnology(technology);
        content.setMessage("This is a sample Test mail where the body is purely HTML ");
        content.setTechnology(technology);
        return content;
    }

}
