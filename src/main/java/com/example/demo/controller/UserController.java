package com.example.demo.controller;

import com.example.demo.common.Constant;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //Get All Users
    @GetMapping(value = "/AllUsers")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

}
