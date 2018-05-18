package com.nwnu.greencloud.service.serviceImpl;

import com.nwnu.greencloud.common.constenum.UserStateEnum;
import com.nwnu.greencloud.domain.UserEntity;
import com.nwnu.greencloud.repository.UserRepository;
import com.nwnu.greencloud.service.EmailService;
import com.nwnu.greencloud.service.UserService;
import com.nwnu.greencloud.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
public class UserServiceImpl  implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Override
    public Boolean register(String username, String password) {
        UserEntity userEntity = null;
        if((userEntity = userRepository.findByUsername(username) )!=null){
            if(userEntity.getState() == UserStateEnum.UNACTIVATE.getState()) {
                emailService.sendRegistEmail(username);
            }
            return false;
        }
        userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        String uuid = UuidUtil.generateUuid();
        userEntity.setApiKey(uuid);
        userEntity.setState(UserStateEnum.UNACTIVATE.getState());
        userRepository.save(userEntity);
        emailService.sendRegistEmail(username);
        return true;
    }

    @Override
    public Boolean login(String username, String password, HttpSession session) {
        UserEntity userEntity;
        if((userEntity = userRepository.findByUsernameAndPassword(username,password)) == null){
            return false;
        }
        if (userEntity.getState() == UserStateEnum.UNACTIVATE.getState()){
            return false;
        }
        session.setAttribute("user",userEntity);
        return true;
    }

    @Override
    public Boolean registerDouble(String apikey) {
        UserEntity userEntity = userRepository.findByApiKey(apikey);
        if(userEntity == null){
            return false;
        }
        userEntity.setState(UserStateEnum.ACTIVATE.getState());
        userRepository.save(userEntity);
        return true;
    }

    @Override
    public Boolean checkUserApiKey(String apikey) {
        if(userRepository.findByApiKey(apikey) != null){
            return true;
        }
        return false;
    }
}
