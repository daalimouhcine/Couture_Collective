package com.example.backend.dto;

import com.example.backend.responses.ClientResponse;
import com.example.backend.responses.TailorResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProjectDto {
    private Long id;
    private String title;
    private String description;
    private List<String> images;
    private String type;
    private List<String> keywords;
    private LocalDateTime createdAt;
    private LocalDate deadline;
    private float price;
    private Boolean show_price;
    private Boolean is_paid;
    private Boolean is_completed;
    private Boolean show_to_public;
    private String visibility_code;
    private TailorDto tailor;
    private ClientDto client;
}
