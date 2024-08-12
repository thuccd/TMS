package com.cateam.pki.tms.configuration;

import com.cateam.pki.tms.entities.Roles;
import com.cateam.pki.tms.entities.Users;
import com.cateam.pki.tms.repositories.RolesRepository;
import com.cateam.pki.tms.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
@Slf4j
public class ApplicationInitConfig {
    /*taoj 1 user admin mặc định khi khởi chạy DV, nếu tồn tại thì thôi
    * tạm thời set trong DB k cần dùng đến class  này */
    /*@Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    ApplicationRunner applicationRunner (UsersRepository usersRepository,RolesRepository rolesRepository){
        return args -> {
            Roles roles = rolesRepository.findByRoleName("Admin");
           if(usersRepository.findByUserName("admin").getUserName().isEmpty()|| usersRepository.findByUserName("admin").getUserName()== null){
               Users users = new Users();
               users.setUserName("admin");
               users.setRoles(Arrays.asList(roles));
               users.setUserPassword(passwordEncoder.encode("admin"));
               usersRepository.save(users);
               log.warn("admin user has been create with default password admin");
           }
        };
    }*/
}
