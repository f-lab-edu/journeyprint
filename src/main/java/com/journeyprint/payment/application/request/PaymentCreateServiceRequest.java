package com.journeyprint.payment.application.request;

import com.journeyprint.payment.domain.Payment;

public record PaymentCreateServiceRequest(Long profileId, String name, String description) {

    public Payment toEntity() {
        return Payment.of(profileId, name, description);
    }
}