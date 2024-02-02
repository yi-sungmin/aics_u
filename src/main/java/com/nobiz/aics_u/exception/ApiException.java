package com.nobiz.aics_u.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {
    private final HttpStatusCode status;
    private final String body;
}
