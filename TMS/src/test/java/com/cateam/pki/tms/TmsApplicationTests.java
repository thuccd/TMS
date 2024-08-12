package com.cateam.pki.tms;

import com.cateam.pki.tms.security.repositories.RolesRepository;
import com.cateam.pki.tms.security.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
