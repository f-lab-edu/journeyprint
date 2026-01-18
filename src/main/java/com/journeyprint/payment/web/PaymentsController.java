package com.journeyprint.payment.web;

import com.journeyprint.common.web.ApiResponse;
import com.journeyprint.payment.application.PaymentService;
import com.journeyprint.payment.web.request.PaymentCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PaymentsController {

    private final PaymentService paymentService;

    @PostMapping("/api/payments")
    ResponseEntity<ApiResponse<Void>> createPayment(@Valid @RequestBody PaymentCreateRequest request) {
        paymentService.createPayment(request.toCommand());
        return ApiResponse.noContent();
    }
}