package com.cateam.pki.tms.controllers;

import com.cateam.pki.tms.dto.request.CertificateRequest;
import com.cateam.pki.tms.entities.Token;
import com.cateam.pki.tms.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ThucCD
 */
@RestController
public class CertificateController {
    @Autowired
    private TokenService tokenService;
    @PostMapping("/tk")
    Token createtk (@RequestBody CertificateRequest request){
        return tokenService.createToken(request);
    }

}
