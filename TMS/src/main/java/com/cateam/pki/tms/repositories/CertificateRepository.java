package com.cateam.pki.tms.repositories;

import com.cateam.pki.tms.entities.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ThucCD
 */
public interface CertificateRepository extends JpaRepository<Certificate,Long> {
}
