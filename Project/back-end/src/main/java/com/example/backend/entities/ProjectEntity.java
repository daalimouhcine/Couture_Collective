package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String images; //////////////
    private String type;
    private String keywords; ////////////////
    private LocalDateTime createdAt;
    private LocalDateTime deadline;
    private float price;
    private Boolean is_paid;
    private Boolean is_completed;
    private Boolean show_to_public;
    private int visibility_code;

    @ManyToOne
    @JoinColumn(name = "fk_tailor_id")
    private TailorEntity tailor;

    @ManyToOne
    @JoinColumn(name = "fk_client_id")
    private ClientEntity client;


}
