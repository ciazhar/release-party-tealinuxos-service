package com.ciazhar.releasepartyservice.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * Created by ciazhar on 01/12/17.
 */
@Data
@Builder
public class PaymentForm {
//    @ParticipantMustExists
    private String participantId;
}
