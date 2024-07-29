package com.cateam.pki.tms.repositories;

import com.cateam.pki.tms.entities.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ThucCD
 */
public interface IncidentRepository extends JpaRepository<Incident,Long> {
}
