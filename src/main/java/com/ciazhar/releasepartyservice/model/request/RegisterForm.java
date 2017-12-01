package com.ciazhar.releasepartyservice.model.request;

import com.ciazhar.releasepartyservice.model.DVDBit;
import com.ciazhar.releasepartyservice.model.JobStatus;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;

/**
 * Created by ciazhar on 01/12/17.
 */

@Data
@Builder
public class RegisterForm {
    private String name;
    private String phoneNumber;
    @Email private String email;
    private JobStatus status;
    private DVDBit dvdBit;
}
