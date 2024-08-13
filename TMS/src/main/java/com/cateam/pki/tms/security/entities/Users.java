package com.cateam.pki.tms.security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author ThucCD
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
     Integer userId;
     String userName;
     String userPassword;




//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id",
//                    referencedColumnName = "userId"),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))
//    private Collection<Roles> roles;

}
