package com.Complete_login_module_JWT.Complete_login_module_JWT.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private  Long id;

    @NotBlank
    @Size(min = 4, max = 50)
    private  String username;
    @NotBlank
    @Size(min = 4, max = 50)
    @Email
    private String email;

}
