package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ElementCollection
    private List<String> images = new ArrayList<String>();
    private String type;
    @ElementCollection
    private List<String> keywords = new ArrayList<String>();
    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDate deadline;
    private float price;
    private Boolean show_price;
    private Boolean is_paid;
    private Boolean is_completed;
    private Boolean show_to_public;
    private String visibility_code;

    @ManyToOne
    @JoinColumn(name = "fk_tailor_id")
    private TailorEntity tailor;

    @ManyToOne
    @JoinColumn(name = "fk_client_id")
    private ClientEntity client;


}
