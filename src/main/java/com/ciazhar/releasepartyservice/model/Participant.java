package com.ciazhar.releasepartyservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by ciazhar on 01/12/17.
 */

@Data
@Builder
@Document
public class Participant {
    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private JobStatus jobStatus;
    private DVDBit dvdBitBit;
    private Boolean paymentStatus;
    private Boolean attendanceStatus;
}
