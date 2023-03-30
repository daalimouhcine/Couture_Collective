package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gender;
    private String email;
    private String phone;
    @CreationTimestamp
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

    @ManyToOne
    @JoinColumn(name = "fk_tailor_id")
    private TailorEntity tailor;

    @OneToMany(mappedBy = "client")
    private List<ProjectEntity> projects = new ArrayList<>();
}
