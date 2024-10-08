package com.dennis.user_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "company_profiles")
public class CompanyProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String website;
    //private String logo;
    @Lob
    private byte[] registrationCertificate;  // Store the certificate as a BLOB

    @Lob
    private byte[] logo;         // Store the logo as a BLOB
}
