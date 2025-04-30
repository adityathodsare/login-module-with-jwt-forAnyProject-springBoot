package com.Complete_login_module_JWT.Complete_login_module_JWT.DTO;


import lombok.Data;

@Data
public class LoginResponseDTO {
    private String JWTtoken;
    private UserDTO userdto;
}
