package com.nwnu.greencloud;

import com.nwnu.greencloud.domain.UserEntity;
import com.nwnu.greencloud.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("163")
public class GreencloudApplicationTests {

	@Autowired
	UserRepository userRepository;
	@Autowired
	private JavaMailSender mailSender; //自动注入的Bean

	@Value("${spring.mail.username}")
	private String Sender; //读取配置文件中的参数

//	@Test
//	public void sendSimpleMail() throws Exception {
//		MimeMessage message = null;
//		message = mailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(message, true);
//		helper.setFrom(Sender);
//		helper.setTo("524633094@qq.com");
//		helper.setSubject("确认注册greencloud账号");
//		StringBuffer sb = new StringBuffer();
//		sb.append("<h1>如果不是您发的邮件，请不要点击下方链接</h1>")
//				.append("<href>www.baidu.com</href>");
//		helper.setText(sb.toString(), true);
//		mailSender.send(message);
//	}
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
