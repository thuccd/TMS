package com.cateam.pki.tms.security.repositories;

import com.cateam.pki.tms.security.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ThucCD
 */
@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByUserName(String userName);

}
