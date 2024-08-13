package com.cateam.pki.tms.security.configuration;

import com.cateam.pki.tms.security.dto.request.UserCreateRquest;
import com.cateam.pki.tms.security.entities.Roles;
import com.cateam.pki.tms.security.entities.Users;
import com.cateam.pki.tms.security.entities.UsersRoles;
import com.cateam.pki.tms.security.repositories.RolesRepository;
import com.cateam.pki.tms.security.repositories.UsersRepository;
import com.cateam.pki.tms.security.repositories.UsersRolesRepository;
import com.cateam.pki.tms.security.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
public class ApplicationInitConfig {
    /*tao 1 user admin mặc định khi khởi chạy DV, nếu tồn tại thì thôi
    * */
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    ApplicationRunner applicationRunner (UsersRepository usersRepository, RolesRepository rolesRepository, UsersRolesRepository usersRolesRepository){
        return args -> {
           if(usersRepository.findByUserName("admin") == null ){
               Roles roles = new Roles();
               roles.setRoleName("Admin");
               rolesRepository.save(roles);

               Users users = new Users();
               users.setUserName("admin");
               users.setUserPassword(passwordEncoder.encode("admin"));
               usersRepository.save(users);

               UsersRoles usersRoles = new UsersRoles();
               usersRoles.setUsers(users);
               usersRoles.setRoles(roles);
               usersRolesRepository.save(usersRoles);

               log.warn("admin user has been create with default password admin");
           }
        };
    }
}
