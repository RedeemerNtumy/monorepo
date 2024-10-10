package com.skillspace.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String requirement;
    private String requiredEarnedBadges;
    private String additionalEarnedBadges;
    private LocalDate dateOfCommencement;
    private LocalDate dateOfCompletion;
    private String status;
    private boolean isDraft;
    private String coverImageForProgram;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean deleted = false;

}
