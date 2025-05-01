package com.Complete_login_module_JWT.Complete_login_module_JWT.Controllers;

import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.RegisterRequestDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.UserDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> RegisteradminUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authService.registerAdminUser(registerRequestDTO));
    }
}
