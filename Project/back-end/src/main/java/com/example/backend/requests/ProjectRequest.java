package com.example.backend.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProjectRequest {
    private Long tailorId;
    private Long clientId;
    private String title;
    private String description;
    private List<String> images = new ArrayList<String>();
    private String type;
    private String keywords;
    private LocalDateTime createdAt;
    private LocalDate deadline;
    private float price;
    private Boolean show_price;
    private Boolean show_to_public;
    private String visibility_code;
}
