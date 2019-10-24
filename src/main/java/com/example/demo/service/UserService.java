package com.example.demo.service;

import com.example.demo.converter.UserConverter;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    //User save
    @Transactional
    public String userSaveOrUpdate(UserDto userDto) {

        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        userRepository.save(user);

        return "user saved";
    }

    //Get all users
    @Transactional
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userConverter::entityToDto).collect(Collectors.toList());
//        return userRepository.findAll();
    }

}
