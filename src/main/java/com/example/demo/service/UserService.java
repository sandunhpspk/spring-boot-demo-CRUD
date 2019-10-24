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

        //check existing user ID
        UserDto userCheck = getUser(user.getId());

        if (userCheck.getId() != user.getId()) {
            userRepository.save(user);
            return "user saved successfully !";
        } else if (userCheck.getId() == user.getId()) {

            User userUpdate = userRepository.findById(user.getId()).get();
            userUpdate.setUsername(userDto.getUsername());
            userUpdate.setPassword(userDto.getPassword());
            userUpdate.setEmail(userDto.getEmail());
            userRepository.save(userUpdate);

            return "user updated successfully !";
        }
        return "";
    }

    //Get all users
    @Transactional
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userConverter::entityToDto).collect(Collectors.toList());
    }

    //Get user by ID
    @Transactional
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElse(new User());

        return userConverter.entityToDto(user);
    }

    //Delete user by ID
    @Transactional
    public String deleteUser(Long id) {
        UserDto userDto = getUser(id);

        if (userDto.getId() != 0) {
            userRepository.deleteById(id);
            return "User deleted successfully!";
        } else {
            return "User not found";
        }
    }


}
