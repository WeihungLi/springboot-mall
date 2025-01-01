package com.weihungli.springbootmall.dao;

import com.weihungli.springbootmall.dto.UserRegisterRequest;
import com.weihungli.springbootmall.model.User;

public interface UserDao {


    Integer CreateUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User getUserByEmail(String email);
}
