package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TailorDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private final String role = "TAILOR";

}
