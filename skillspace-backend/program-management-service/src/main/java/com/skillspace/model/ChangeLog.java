package com.skillspace.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class ChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime changeDate;

    private String changedBy;
    private String changeDescription;

}
