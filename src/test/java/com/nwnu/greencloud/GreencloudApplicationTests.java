package com.nwnu.greencloud;

import com.nwnu.greencloud.domain.UserEntity;
import com.nwnu.greencloud.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreencloudApplicationTests {

	@Autowired
	UserRepository userRepository;
	@Test
	public void contextLoads() {
	}

	@Test
	public void addUser(){
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername("test");
		userEntity.setPassword("123456");
		userEntity.setApiKey("222222");
		userEntity.setUsername("test2");
		userEntity.setPassword("123456");
		userEntity.setApiKey("2222221");
	}

}
