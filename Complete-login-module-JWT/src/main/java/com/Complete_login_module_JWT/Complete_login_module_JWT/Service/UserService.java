package com.Complete_login_module_JWT.Complete_login_module_JWT.Service;


import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.ChangePasswordDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.UserDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.Entity.User;
import com.Complete_login_module_JWT.Complete_login_module_JWT.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserService {

    @Autowired
    private  UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;
    //
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found "));
        return convertToUserDTO(user);
    }

    //
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException(" user not found "));
        return convertToUserDTO(user);
    }

    //
    public List<UserDTO> getAllUsers() {
        List<User> listOfUsers  = userRepository.findAll();
        List<UserDTO> listOfDTOs =  listOfUsers.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
        return  listOfDTOs;
    }

    public UserDTO changePassword(Long id, ChangePasswordDTO changePasswordDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found "));
        if (! passwordEncoder.matches( changePasswordDTO.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("password does not match");
        }

        if (! changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
            throw new RuntimeException("password does not match to new password ");
        }
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
        return convertToUserDTO(user);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found "));
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());

        User savedUser = userRepository.save(user);
        return convertToUserDTO(savedUser);

    }

    public Object deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found "));
        userRepository.delete(user);
        return "User deleted";
    }


    public UserDTO  convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
