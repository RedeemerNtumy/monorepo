package com.skillspace.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String skill;
    private String companyName;
    private boolean isGlobal;
    private int timeLimit;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questions;

    public boolean isGlobal(){
        return isGlobal;
    }

    public void setGlobal(boolean global){
        isGlobal = global;
    }

}
