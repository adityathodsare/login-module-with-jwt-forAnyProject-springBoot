package com.Complete_login_module_JWT.Complete_login_module_JWT.DTO;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {
    private String JWTtoken;
    private UserDTO userdto;
}
