package com.cateam.pki.tms.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ThucCD
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateRequest {
    private int certificate_id;
    private String certificate_serial_number;
    private String subject_dn;
    private Date not_before;
    private Date not_after;
    private Date revocation_date;
    private int incident_id;
    private int token_id;
    private boolean is_remote_import;
    private String ca;
}
