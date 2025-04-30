package com.Complete_login_module_JWT.Complete_login_module_JWT.DTO;


import lombok.Data;

@Data
public class ChangePasswordDTO {
    private  String oldPassword;
    private  String newPassword;
    private  String confirmPassword;
}
