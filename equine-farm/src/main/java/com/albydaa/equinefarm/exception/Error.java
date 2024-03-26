package com.albydaa.equinefarm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class Error {
    private String message;
    private LocalDateTime time;
    private HttpStatusCode statusCode;
}





