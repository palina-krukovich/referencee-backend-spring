package com.referencee.api;

import com.referencee.api.util.AuthUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiApplicationTests {

	@Test
	void contextLoads() {
		AuthUtil.getInstance();
	}

}
