package com.nobiz.aics_u;

import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Slf4j
class AicsUApplicationTests {

	@Test
	void contextLoads() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encode = passwordEncoder.encode("1234");
		System.out.println(encode);
	}

}
