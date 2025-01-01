package com.weihungli.springbootmall.service.Impl;


import com.weihungli.springbootmall.dao.UserDao;
import com.weihungli.springbootmall.dto.UserLoginRequest;
import com.weihungli.springbootmall.dto.UserRegisterRequest;
import com.weihungli.springbootmall.model.User;
import com.weihungli.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;



@Component
public class UserServiceImpl implements UserService {


    private  final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        //檢查Email是否被註冊過
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
        if(user != null){
            log.warn("該 Email:{} 已被註冊",userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //密碼使用Hash加密(MD5)
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);

        return userDao.CreateUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer UserId) {
        User user = userDao.getUserById(UserId);
        return user;
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        if(user != null){
            //密碼使用Hash驗證是否相同
            String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

            if(user.getPassword().equals(hashedPassword)) {
                return user;
            }
            else{
                log.warn("該 Password:{} 並非該Email:{} 之密碼",userLoginRequest.getPassword(),userLoginRequest.getEmail());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        else{
            log.warn("該 Email:{} 尚未註冊",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
