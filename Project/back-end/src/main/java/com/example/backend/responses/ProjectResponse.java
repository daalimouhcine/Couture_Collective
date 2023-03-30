package com.example.backend.responses;

import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProjectResponse {
    private Long id;
    private String title;
    private String description;
    private List<String> images = new ArrayList<String>();
    private String type;
    private List<String> keywords = new ArrayList<String>();
    private LocalDateTime createdAt;
    private LocalDateTime deadline;
    private float price;
    private Boolean show_price;
    private Boolean is_paid;
    private Boolean is_completed;
    private Boolean show_to_public;
    private int visibility_code;
    private ClientResponse client;
    private TailorResponse tailor;
}
