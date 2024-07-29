package com.cateam.pki.tms.repositories;

import com.cateam.pki.tms.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ThucCD
 */
@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
    Roles findByRoleName(String roleName);
}
