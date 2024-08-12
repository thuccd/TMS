package com.cateam.pki.tms.services;

import com.cateam.pki.tms.dto.request.AuthenticationRequest;
import com.cateam.pki.tms.dto.request.IntrospectRequest;
import com.cateam.pki.tms.dto.response.AuthenticationResponse;
import com.cateam.pki.tms.dto.response.IntrospectResponse;
import com.cateam.pki.tms.entities.Roles;
import com.cateam.pki.tms.entities.Users;
import com.cateam.pki.tms.exception.ApiException;
import com.cateam.pki.tms.repositories.RolesRepository;
import com.cateam.pki.tms.repositories.UsersRepository;
import com.cateam.pki.tms.utils.ConstantValue;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    RolesRepository rolesRepository;
    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder ;
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
                .claim("scope",roleName)
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header,payload);
        jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
        return jwsObject.serialize();
    }

}
