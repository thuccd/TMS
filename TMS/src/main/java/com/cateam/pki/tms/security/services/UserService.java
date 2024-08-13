package com.cateam.pki.tms.security.services;

import com.cateam.pki.tms.exception.ApiException;
import com.cateam.pki.tms.security.dto.request.UserCreateRquest;
import com.cateam.pki.tms.security.dto.response.UserResponse;
import com.cateam.pki.tms.security.entities.Roles;
import com.cateam.pki.tms.security.entities.Users;
import com.cateam.pki.tms.security.entities.UsersRoles;
import com.cateam.pki.tms.security.repositories.RolesRepository;
import com.cateam.pki.tms.security.repositories.UsersRepository;
import com.cateam.pki.tms.security.repositories.UsersRolesRepository;
import com.cateam.pki.tms.utils.ConstantValue;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Slf4j
public class UserService {
    UsersRepository usersRepository;
    RolesRepository rolesRepository;
    PasswordEncoder passwordEncoder;
    UsersRolesRepository usersRolesRepository;

    @PreAuthorize("hasRole('Admin')")
    public UserResponse createUser ( UserCreateRquest userCreateRquest){
        Roles roles = new Roles();
        roles = rolesRepository.findByRoleName(userCreateRquest.getRole());
        if(roles==null){
            log.info("Role is not found");
            throw  new ApiException(ConstantValue.ErrorCode.ROLE_NOT_FOUND);
        }
        Users users = new Users() ;
        users= usersRepository.findByUserName(userCreateRquest.getUserName());
        if(users!=null){
            log.info("User existed");
            throw  new ApiException(ConstantValue.ErrorCode.USER_EXISTED);
        }
        users.setUserName(userCreateRquest.getUserName());
        users.setUserPassword(passwordEncoder.encode(userCreateRquest.getUserPassword()));
        usersRepository.save(users);

        UsersRoles usersRoles = new UsersRoles();
        usersRoles.setUsers(users);
        usersRoles.setRoles(roles);
        usersRolesRepository.save(usersRoles);

        UserResponse userResponse = new UserResponse();
        userResponse.setRole(roles.getRoleName());
        userResponse.setUserName(users.getUserName());
       return  userResponse;
    }

    public List<Users> getUser(){
        return usersRepository.findAll();
    }

}
