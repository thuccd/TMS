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
@Table(name = "certificate_request_history")
public class CertificateRequestHistory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer certificateRequestHistoryId;
     String email = "";
     int certificateRequestHistoryOldId;
     String otherFormatRequest;
     int genKey;
     String sdt;

    @OneToOne
    @JoinColumn(name = "incidentId")
    private Incident incident;
}
