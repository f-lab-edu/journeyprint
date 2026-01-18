package com.journeyprint.common.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse<T> {

    private final int code;
    private final HttpStatus status;
    private final String message;
    private final T data;

    private ApiResponse(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseEntity<ApiResponse<T>> of(HttpStatus status, String message, T data) {
        ApiResponse<T> body = new ApiResponse<>(status, message, data);

        return ResponseEntity
                .status(status)
                .body(body);
    }

    public static <T> ResponseEntity<ApiResponse<T>> noContent() {
        HttpStatus status = HttpStatus.NO_CONTENT;
        ApiResponse<T> body = new ApiResponse<>(status, null, null);

        return ResponseEntity
                .status(status)
                .body(body);
    }

    public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return of(HttpStatus.OK, "Success", data);
    }
}