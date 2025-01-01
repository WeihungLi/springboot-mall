package com.weihungli.springbootmall.service;

import com.weihungli.springbootmall.dto.UserLoginRequest;
import com.weihungli.springbootmall.dto.UserRegisterRequest;
import com.weihungli.springbootmall.model.User;

public interface UserService {


    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer id);

    User login(UserLoginRequest userLoginRequest);
}
