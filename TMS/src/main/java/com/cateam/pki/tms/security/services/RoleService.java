package com.cateam.pki.tms.security.services;

import com.cateam.pki.tms.exception.ApiException;
import com.cateam.pki.tms.security.dto.request.RoleCreateRequest;
import com.cateam.pki.tms.security.entities.Roles;
import com.cateam.pki.tms.security.repositories.RolesRepository;
import com.cateam.pki.tms.utils.ConstantValue;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleService {

    RolesRepository rolesRepository;
    @PreAuthorize("hasRole('Admin')")
    public Roles createRole (RoleCreateRequest roleCreateRequest){
        System.out.println(2);
        Roles roles= new Roles();
        roles = rolesRepository.findByRoleName(roleCreateRequest.getRoleName());
        if(roles!=null){
            log.info("Role existed");
            throw  new ApiException(ConstantValue.ErrorCode.ROLE_EXISTED);
        }
        roles.setRoleName(roleCreateRequest.getRoleName());
        return rolesRepository.save(roles);
    }
}
