package com.example.backend.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String token;
    private TailorResponse tailorResponse;
    private String message;
    private Boolean success;
}
