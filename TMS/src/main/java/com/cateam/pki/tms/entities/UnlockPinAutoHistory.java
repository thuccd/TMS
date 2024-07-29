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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "unlock_pin_auto_history")
public class UnlockPinAutoHistory implements Serializable {
    @Serial
    private  static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     int unlockPinAutoId ;
     int tokenId ;
     String certificateSerialNumber ;
     String authenticationCode;
      int numberRequested;
     int numberAuthenticationRequested;
     Date dateTimeRequest ;
     int status;

    @OneToOne
    @JoinColumn(name = "incidentId")
    private Incident incident;
}
