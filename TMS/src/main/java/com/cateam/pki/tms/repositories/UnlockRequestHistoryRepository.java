package com.cateam.pki.tms.repositories;

import com.cateam.pki.tms.entities.UnlockRequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ThucCD
 */
public interface UnlockRequestHistoryRepository extends JpaRepository<UnlockRequestHistory,Long> {
}
