package com.hoanghuy2209.userservice.service;

import com.hoanghuy2209.userservice.data.User;
import com.hoanghuy2209.userservice.model.UserDTO;

import java.util.List;

public interface IUserService {
    List<User> getAllUser();
    User saveUser(User user);
    UserDTO login(String username, String password);
    UserDTO validateToken(String token);
}