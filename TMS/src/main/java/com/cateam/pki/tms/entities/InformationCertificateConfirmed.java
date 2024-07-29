package com.cateam.pki.tms.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ThucCD
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="infomation_certificate_confirmed")
public class InformationCertificateConfirmed implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     int id;
     String data;
     Date timeCreate;

    @OneToOne
    @JoinColumn(name="certificateSerialNumber")
    Certificate certificate;
}
