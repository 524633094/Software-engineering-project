package com.nwnu.greencloud.service.serviceImpl;

import com.nwnu.greencloud.repository.UserRepository;
import com.nwnu.greencloud.service.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
* @Author zhangqi
* @Desccription: 邮件服务
* @Date: 11:36 2018/5/5
*/
@Log4j2
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private  String sender;
    private static final String PREFIX = "http://123.207.124.113:8080/api/registdouble/";
    public Boolean sendRegistEmail(String receiver){
        try{
        MimeMessage message = null;
		message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(sender);
		helper.setTo(receiver);
		helper.setSubject("确认注册greencloud账号");
		StringBuffer sb = new StringBuffer();
		String apikey = userRepository.findByUsername(receiver).getApiKey();
		sb.append("<h1>如果不是您发的邮件，请不要点击下方链接，复制下方地址到浏览器完成注册</h1>")
				.append("<href>"+PREFIX+apikey+"</href>");
		helper.setText(sb.toString(), true);
		mailSender.send(message);
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

}
