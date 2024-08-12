package com.cateam.pki.tms.repositories;

import com.cateam.pki.tms.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ThucCD
 */
@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByUserName(String userName);

}
