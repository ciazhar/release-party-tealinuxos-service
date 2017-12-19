package com.ciazhar.releasepartyservice.service;

import com.ciazhar.releasepartyservice.model.request.RegisterForm;

/**
 * Created by ciazhar on 02/12/17.
 * [ Documentatiion Here ]
 */
public interface EmailService {
    void sendEmail(RegisterForm form);
}
