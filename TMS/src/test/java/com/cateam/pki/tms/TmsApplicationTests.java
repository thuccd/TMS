package com.cateam.pki.tms;

import com.cateam.pki.tms.entities.Roles;
import com.cateam.pki.tms.entities.Users;
import com.cateam.pki.tms.repositories.RolesRepository;
import com.cateam.pki.tms.repositories.UsersRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.management.relation.Role;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
class TmsApplicationTests {
	@Autowired
	RolesRepository rolesRepository;
	@Autowired
	UsersRepository usersRepository;

	@Test
	void contextLoads() {
		String rolename = rolesRepository.selectRoleNameByUserName("ThucCD5");
		System.out.println("roleName:"+rolename);

	}

}
