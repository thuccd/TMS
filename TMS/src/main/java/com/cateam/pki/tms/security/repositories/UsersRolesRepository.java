package com.cateam.pki.tms.security.repositories;

import com.cateam.pki.tms.security.entities.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRolesRepository extends JpaRepository<UsersRoles,Long> {
}
