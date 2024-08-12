package com.cateam.pki.tms.services;

import com.cateam.pki.tms.dto.request.UserCreateRquest;
import com.cateam.pki.tms.entities.Roles;
import com.cateam.pki.tms.entities.Users;
import com.cateam.pki.tms.repositories.RolesRepository;
import com.cateam.pki.tms.repositories.UsersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
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
