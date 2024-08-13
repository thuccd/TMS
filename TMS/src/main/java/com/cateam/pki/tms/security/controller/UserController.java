package com.cateam.pki.tms.security.controller;

import com.cateam.pki.tms.security.dto.request.RoleCreateRequest;
import com.cateam.pki.tms.security.dto.request.UserCreateRquest;
import com.cateam.pki.tms.security.dto.response.ApiResponse;
import com.cateam.pki.tms.security.dto.response.UserResponse;
import com.cateam.pki.tms.security.entities.Roles;
import com.cateam.pki.tms.security.entities.Users;
import com.cateam.pki.tms.security.services.RoleService;
import com.cateam.pki.tms.security.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
     UserService userService;
     RoleService roleService;
    @PostMapping("/create_user")
    ApiResponse<UserResponse> createUser (@RequestBody UserCreateRquest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
       return apiResponse;
    }

    @PostMapping("/create_role")
    ApiResponse<Roles> createRole (@RequestBody RoleCreateRequest request){
        System.out.println(1);
        ApiResponse<Roles> apiResponse = new ApiResponse<>();
        apiResponse.setResult(roleService.createRole(request));
        return  apiResponse;
    }


}
