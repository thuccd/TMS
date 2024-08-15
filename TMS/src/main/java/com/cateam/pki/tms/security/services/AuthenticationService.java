package com.cateam.pki.tms.security.services;

import com.cateam.pki.tms.security.dto.request.AuthenticationRequest;
import com.cateam.pki.tms.security.dto.request.IntrospectRequest;
import com.cateam.pki.tms.security.dto.request.RefreshRequest;
import com.cateam.pki.tms.security.dto.response.AuthenticationResponse;
import com.cateam.pki.tms.security.dto.response.IntrospectResponse;
import com.cateam.pki.tms.security.entities.InvalidatedToken;
import com.cateam.pki.tms.security.entities.Users;
import com.cateam.pki.tms.exception.ApiException;
import com.cateam.pki.tms.security.repositories.InvalidatedTokenRepository;
import com.cateam.pki.tms.security.repositories.RolesRepository;
import com.cateam.pki.tms.security.repositories.UsersRepository;
import com.cateam.pki.tms.utils.ConstantValue;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    RolesRepository rolesRepository;
    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder ;
    InvalidatedTokenRepository invalidatedTokenRepository;
    @NonFinal
    protected  static  final  String SIGNER_KEY = "fP3LnWWxgT5OULI3qasWUhJiZyUc6ZRkdt2TiJ3akA8iuG0F1TVcANOsjDE3JgwT\n";

    public IntrospectResponse introspect (IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);
        return IntrospectResponse.builder()
                .valid(verified && expityTime.after(new Date()))
                .build();
    }

   public AuthenticationResponse authenticate(AuthenticationRequest request) throws JOSEException {
        var user = usersRepository.findByUserName(request.getUserName());
        if(user == null){
           throw new ApiException(ConstantValue.ErrorCode.USER_NOT_EXISTED);
        }else{
            boolean authenticated = passwordEncoder.matches(request.getPassword(),user.getUserPassword());
            if(!authenticated){
                throw new ApiException(ConstantValue.ErrorCode.UNAUTHENTICATED);
            }else{
             var token = generateToken(user);
             return AuthenticationResponse.builder()
                     .token(token)
                     .authenticated(true)
                     .build();
            }

        }
    }

    private String generateToken(Users user) throws JOSEException {
        String roleName = rolesRepository.selectRoleNameByUserName(user.getUserName());
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("pki.tms")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope",roleName)
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header,payload);
        jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
        return jwsObject.serialize();
    }


    public AuthenticationResponse refreshToken(RefreshRequest refreshRequest) throws ParseException, JOSEException {
        var signedJWT = verifyToken(refreshRequest.getToken());

        var jit = signedJWT.getJWTClaimsSet().getJWTID();
        var  expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expiryTime)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);
        var userName = signedJWT.getJWTClaimsSet().getSubject();

        var user = usersRepository.findByUserName(userName);

        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }



    private SignedJWT verifyToken (String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if(!(verified && expiryTime.after(new Date()))) throw new ApiException(ConstantValue.ErrorCode.UNAUTHENTICATED);
        return signedJWT;
    }

}
