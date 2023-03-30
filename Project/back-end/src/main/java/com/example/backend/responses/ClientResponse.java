package com.example.backend.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientResponse {
    private Long id;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private String measuringUnit;
    private int height;
    private int armLength;
    private int legLength;
    private int waist;
    private int chest;
    private int hips;
    private int neck;
    private int shoulder;
    private int biceps;
}
