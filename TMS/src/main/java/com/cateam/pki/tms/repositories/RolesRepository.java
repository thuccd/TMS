package com.cateam.pki.tms.repositories;

import com.cateam.pki.tms.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author ThucCD
 */
@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
    Roles findByRoleName(String roleName);

    @Query(value = "SELECT r.role_name from users u INNER JOIN users_roles ur ON u.user_id = ur.user_id INNER JOIN roles r on ur.role_id = r.role_id where u.user_name = ? ", nativeQuery = true)
    public  String selectRoleNameByUserName(String userName);

}
