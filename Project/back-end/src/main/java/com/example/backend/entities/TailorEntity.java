package com.example.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tailors")
public class TailorEntity {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "tailor")
    private List<ProjectEntity> projects = new ArrayList<>();

    @OneToMany(mappedBy = "tailor")
    private List<ClientEntity> clients = new ArrayList<>();


}
