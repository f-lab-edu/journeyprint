package com.journeyprint.payment.application;

import com.journeyprint.common.exception.ApiException;
import com.journeyprint.payment.application.request.PaymentCreateServiceRequest;
import com.journeyprint.payment.domain.PaymentRepository;
import com.journeyprint.profile.domain.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final ProfileRepository profileRepository;
    private final PaymentRepository paymentRepository;

    public void createPayment(PaymentCreateServiceRequest request) {
        // TODO: ProfileService 검증 로직으로 변경 필요 && 프로필에서 CustomExcpetion 생성하여 던지기
        if (profileRepository.getById(request.profileId()).isEmpty()) {
            throw new ApiException("Profile not found");
        }

        paymentRepository.save(request.toEntity());
    }
}