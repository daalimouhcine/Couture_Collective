package com.example.backend.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResponse {
    private String message;
    private Boolean success;
}
