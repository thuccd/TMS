package com.cateam.pki.tms.security.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

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
