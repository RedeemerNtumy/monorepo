package com.skillspace.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ElementCollection
    private List<String> options;
    private int correctOptionIndex;
    private int pointValue;
}
