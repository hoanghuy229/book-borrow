package com.hoanghuy2209.userservice.controller;

import com.hoanghuy2209.userservice.data.User;
import com.hoanghuy2209.userservice.model.UserDTO;
import com.hoanghuy2209.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private IUserService userService;


    @GetMapping("")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    @PostMapping("/login")
    public UserDTO login(@RequestBody UserDTO dto) {
        return userService.login(dto.getUsername(), dto.getPassword());
    }
}
