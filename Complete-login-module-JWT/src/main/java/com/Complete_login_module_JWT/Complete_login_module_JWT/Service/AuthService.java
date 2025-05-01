package com.Complete_login_module_JWT.Complete_login_module_JWT.Service;

import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.LoginRequestDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.LoginResponseDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.RegisterRequestDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.UserDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.Entity.User;
import com.Complete_login_module_JWT.Complete_login_module_JWT.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO registerNormalUser(RegisterRequestDTO registerRequestDTO) {
        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
            throw  new RuntimeException("Username already exists");
        }
        Set<String> set = new HashSet<>();
        set.add("ROLE_USER");

         User user = new User();
         user.setUsername(registerRequestDTO.getUsername());
         user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
         user.setEmail(registerRequestDTO.getEmail());
         user.setRoles(set);

         User savedUser = userRepository.save(user);
         return  convertToUserDTO(savedUser);

    }

    public UserDTO  convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }













    public static LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        return null;
    }

    public static ResponseEntity<String> logout() {
    }


    public UserDTO registerAdminUser(RegisterRequestDTO registerRequestDTO) {
        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
            throw  new RuntimeException("Username already exists");
        }
        Set<String> set = new HashSet<>();
        set.add("ROLE_ADMIN");
        set.add("ROLE_USER");

        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setEmail(registerRequestDTO.getEmail());
        user.setRoles(set);

        User savedUser = userRepository.save(user);
        return  convertToUserDTO(savedUser);
    }
    
}



