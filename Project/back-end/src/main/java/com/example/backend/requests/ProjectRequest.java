package com.example.backend.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProjectRequest {
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
}
