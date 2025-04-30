package com.Complete_login_module_JWT.Complete_login_module_JWT.Controllers;


import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.LoginRequestDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.LoginResponseDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.RegisterRequestDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.UserDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class  AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> RegisterNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(AuthService.registerNormalUSer(registerRequestDTO));
    }

    @PostMapping("login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = AuthService.login(loginRequestDTO);
        ResponseCookie Cookie = ResponseCookie.from("jwt", loginResponseDTO.getJWTtoken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(1*60*60)
                .sameSite("strict")
                .build();

        return  ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, Cookie.toString() )
                .body(loginResponseDTO.getUserdto());
    }


    @PostMapping("/logout")
    public  ResponseEntity<String> logout(){
        return  AuthService.logout();
    }

}
