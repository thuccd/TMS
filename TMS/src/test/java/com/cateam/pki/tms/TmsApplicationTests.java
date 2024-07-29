package com.cateam.pki.tms;

import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class TmsApplicationTests {

	@Test
	void contextLoads() throws NoSuchMethodException, NoSuchAlgorithmException {
		String password = "123456";
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String md5Hash = DatatypeConverter.printHexBinary(digest);
		System.out.println(md5Hash);
		md.update(password.getBytes());
		


	}

}
