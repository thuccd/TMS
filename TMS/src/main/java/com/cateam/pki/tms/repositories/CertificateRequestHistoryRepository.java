package com.cateam.pki.tms.repositories;

import com.cateam.pki.tms.entities.CertificateRequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ThucCD
 */
public interface CertificateRequestHistoryRepository extends JpaRepository<CertificateRequestHistory,Long> {
}
