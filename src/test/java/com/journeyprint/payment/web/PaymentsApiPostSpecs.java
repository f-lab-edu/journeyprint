package com.journeyprint.payment.web;

import static org.assertj.core.api.Assertions.assertThat;

import com.journeyprint.JourneyprintApiTest;
import com.journeyprint.payment.web.request.PaymentCreateRequest;
import com.journeyprint.profile.domain.ProfileRepository;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("/api/payments")
class PaymentsApiPostSpecs extends JourneyprintApiTest {

    @Autowired
    ProfileRepository profileRepository;

    @Test
    void 올바르게_요청하면_204_No_Content_상태코드를_반환한다() {
        // Arrange
        var profileId = 1L;
        profileRepository.save(profileId);

        PaymentCreateRequest request = new PaymentCreateRequest(
                profileId,
                "테스트 카드",
                null
        );

        // Act
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/api/payments");

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(204);
    }
    
    @Test
    void profile_id_속성을_입력하지_않으면_400_Bad_Request_상태코드를_반환한다() {
        // Arrange
        PaymentCreateRequest request = new PaymentCreateRequest(
                null,
                "테스트 카드",
                null
        );
        
        // Act
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/api/payments");
        
        // Assert
        assertThat(response.getStatusCode()).isEqualTo(400);
    }
    
    @Test
    void profile_id_에_해당하는_사용자가_존재하지_않으면_400_Bad_Request_상태코드를_반환한다() {
        // Arrange
        PaymentCreateRequest request = new PaymentCreateRequest(
                9999L,
                "테스트 카드",
                null
        );
        
        // Act
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/api/payments");
        
        // Assert
        assertThat(response.getStatusCode()).isEqualTo(400);
    }
    
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    void name_속성이_null_또는_빈_문자열이면_400_Bad_Request_상태코드를_반환한다(String name) {
        // Arrange
        var profileId = 1L;
        profileRepository.save(profileId);

        PaymentCreateRequest request = new PaymentCreateRequest(
                profileId,
                name,
                null
        );

        // Act
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/api/payments");

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(400);
    }

    @Test
    void name_속성이_30자를_초과하면_400_Bad_Request_상태코드를_반환한다() {
        // Arrange
        var profileId = 1L;
        profileRepository.save(profileId);

        PaymentCreateRequest request = new PaymentCreateRequest(
                profileId,
                "1234567890123456789012345678901",
                null
        );

        // Act
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/api/payments");

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(400);
    }
    
    @Test
    void description_속성이_100자를_초과하면_400_Bad_Request_상태코드를_반환한다() {
        // Arrange
        var profileId = 1L;
        profileRepository.save(profileId);

        PaymentCreateRequest request = new PaymentCreateRequest(
                profileId,
                "테스트 카드",
                "a".repeat(101)
        );

        // Act
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/api/payments");

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(400);
    }

}