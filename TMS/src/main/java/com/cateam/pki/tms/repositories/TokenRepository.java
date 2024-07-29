package com.cateam.pki.tms.repositories;

import com.cateam.pki.tms.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ThucCD
 */
public interface TokenRepository extends JpaRepository<Token,Long> {
}
