package com.cateam.pki.tms.controllers;

import com.cateam.pki.tms.dto.request.Request;
import com.cateam.pki.tms.dto.request.UserCreateRquest;
import com.cateam.pki.tms.dto.response.ApiResponse;
import com.cateam.pki.tms.entities.Users;
import com.cateam.pki.tms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/users")
    ApiResponse<Users> createUser (@RequestBody UserCreateRquest request){
        ApiResponse<Users> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
       return apiResponse;
    }



}
