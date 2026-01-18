package com.journeyprint.payment.web.request;

import com.journeyprint.payment.application.request.PaymentCreateServiceRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PaymentCreateRequest(
        @NotNull Long profileId,
        @NotBlank @Size(max = 30) String name,
        @Size(max = 100) String description
) {

    public PaymentCreateServiceRequest toServiceRequest() {
        return new PaymentCreateServiceRequest(profileId, name, description);
    }
}