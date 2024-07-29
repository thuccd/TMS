package com.cateam.pki.tms.services;

import com.cateam.pki.tms.dto.request.CertificateRequest;
import com.cateam.pki.tms.entities.Token;
import com.cateam.pki.tms.repositories.TokenRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ThucCD
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenService {
    TokenRepository tokenRepository;
    public Token createToken (CertificateRequest request){
        Token tk = new Token();
        tk.setTokenSerialNumber("Test1234");
        return tokenRepository.save(tk);
    }
}
