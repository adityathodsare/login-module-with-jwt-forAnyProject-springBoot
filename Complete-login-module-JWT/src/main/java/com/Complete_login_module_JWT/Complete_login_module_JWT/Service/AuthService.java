package com.Complete_login_module_JWT.Complete_login_module_JWT.Service;

import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.LoginRequestDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.LoginResponseDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.RegisterRequestDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    public static UserDTO registerNormalUSer(RegisterRequestDTO registerRequestDTO) {
    }


    public static LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        return null;
    }

    public static ResponseEntity<String> logout() {
    }
}



