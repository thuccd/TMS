package com.cateam.pki.tms.security.dto.request;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRquest {
    String userName;
    String userPassword;
    String role;
}
