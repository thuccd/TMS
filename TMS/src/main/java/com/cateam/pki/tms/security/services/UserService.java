package com.cateam.pki.tms.security.services;

import com.cateam.pki.tms.security.dto.request.UserCreateRquest;
import com.cateam.pki.tms.security.entities.Roles;
import com.cateam.pki.tms.security.entities.Users;
import com.cateam.pki.tms.security.repositories.RolesRepository;
import com.cateam.pki.tms.security.repositories.UsersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author ThucCD
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UsersRepository usersRepository;
    RolesRepository rolesRepository;
    PasswordEncoder passwordEncoder;
    public Users createUser ( UserCreateRquest userCreateRquest){
        Roles roles = new Roles();
        roles = rolesRepository.findByRoleName(userCreateRquest.getRole());

        Users users = new Users() ;
        users.setUserName(userCreateRquest.getUserName());
        users.setUserPassword(passwordEncoder.encode(userCreateRquest.getUserPassword()));
        users.setRoles(Arrays.asList(roles));

       return  usersRepository.save(users);
    }

    public List<Users> getUser(){
        return usersRepository.findAll();
    }

}
