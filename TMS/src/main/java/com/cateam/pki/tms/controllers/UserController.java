package com.cateam.pki.tms.controllers;

import com.cateam.pki.tms.dto.request.Request;
import com.cateam.pki.tms.dto.request.UserCreateRquest;
import com.cateam.pki.tms.dto.response.ApiResponse;
import com.cateam.pki.tms.entities.Users;
import com.cateam.pki.tms.services.UserDetailService;
import com.cateam.pki.tms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    private UserDetailService userDetailsService;

    @PostMapping("/users")
    ApiResponse<Users> createUser (@RequestBody UserCreateRquest request){
        ApiResponse<Users> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
       return apiResponse;
    }

    @PostMapping("/user")
    ApiResponse<UserDetails> getUser (@RequestBody Request request){
        ApiResponse<UserDetails> apiResponse = new ApiResponse<>();
        System.out.println(request.getUserName());
        String a = request.getUserName();
        apiResponse.setResult(userDetailsService.loadUserByUsername(a));
        return apiResponse;
    }

}
