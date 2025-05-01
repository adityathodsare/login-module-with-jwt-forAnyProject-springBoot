package com.Complete_login_module_JWT.Complete_login_module_JWT.Controllers;


import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.LoginRequestDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.LoginResponseDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.RegisterRequestDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.UserDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.Entity.User;
import com.Complete_login_module_JWT.Complete_login_module_JWT.Repository.UserRepository;
import com.Complete_login_module_JWT.Complete_login_module_JWT.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class  AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    // register user to database
    @PostMapping("/register")
    public ResponseEntity<UserDTO> RegisterNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authService.registerNormalUSer(registerRequestDTO));
    }

    // log in the user ny credentials
    @PostMapping("login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = authService.login(loginRequestDTO);
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


    // logout the current logged in user
    @PostMapping("/logout")
    public  ResponseEntity<String> logout(){
        return  authService.logout();
    }


    // get current user from dashboard
    @GetMapping("/getcurrentuser")
    public ResponseEntity<?> getCurrentUser(Authentication authentication){
        if (authentication == null){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("user is not logged in/ UNAUTHORIZED");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found "));


        return ResponseEntity.ok(convertToUserDTO(user));

    }
    public UserDTO  convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

}
