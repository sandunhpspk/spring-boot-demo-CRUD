package com.example.demo.controller;

import com.example.demo.common.Constant;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(Constant.API_ROOT)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //save user
    //v1/web/users
    @PostMapping(value = "/users")
    public String addUser(@RequestBody UserDto userDto) {
        String status = userService.userSaveOrUpdate(userDto);
        return status;
    }


}
