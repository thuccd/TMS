package com.cateam.pki.tms.security.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.interceptor.CacheOperation;

import java.io.Serial;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invalidated_token")
@Builder
public class InvalidatedToken {
    @Id
    String id;
    Date expiryTime;


}
