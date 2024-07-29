package com.cateam.pki.tms.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author ThucCD
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "certificate")
public class Certificate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     int certificateId;
     String certificateSerialNumber;
     int lastImportTokenId;
     boolean isConfirm;
     String base64Certificate;

    @ManyToOne
    @JoinColumn(name= "tokenId")
    private Token token;

    @OneToOne
    @JoinColumn(name = "incidentId")
    private Incident incident;

}
