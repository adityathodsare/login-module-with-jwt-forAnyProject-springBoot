package com.Complete_login_module_JWT.Complete_login_module_JWT.Service;


import com.Complete_login_module_JWT.Complete_login_module_JWT.Entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
public class JwtService {

    public static String generateToken(User user) {
    }
}

