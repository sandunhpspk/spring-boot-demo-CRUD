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
    @PostMapping(value = "/saveUser")
    public String addUser(@RequestBody UserDto userDto) {
        String status = userService.userSaveOrUpdate(userDto);
        return status;
    }

    //Get All Users
    @GetMapping(value = "/getAllUsers")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    //Get user by ID
    @GetMapping(value = "/getUser/{id}")
    public UserDto getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    //Delete User
    @DeleteMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id){
        String status = userService.deleteUser(id);
        return status;
    }

}
