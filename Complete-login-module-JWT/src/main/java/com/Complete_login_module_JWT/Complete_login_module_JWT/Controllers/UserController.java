package com.Complete_login_module_JWT.Complete_login_module_JWT.Controllers;


import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.ChangePasswordDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.DTO.UserDTO;
import com.Complete_login_module_JWT.Complete_login_module_JWT.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // getting user by id from service layer
    @GetMapping("/getuserbyid/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return  ResponseEntity.ok(userService.getUserById(id));
    }

    // getting user by email from service layer
    @GetMapping("getuserbyusername/{username)")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable  String username) {
        return  ResponseEntity.ok(userService.getUserByUsername(username));
    }

    //
    @GetMapping("/getallusers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/changepassword/{id}")
    public ResponseEntity<UserDTO> changePassword(@PathVariable Long id, ChangePasswordDTO changePasswordDTO) {
        return ResponseEntity.ok(userService.changePassword(id,changePasswordDTO));
    }

    @PutMapping("/updateuserbyid/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id , @RequestBody UserDTO userDTO) {
        return  ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/deleteuserbyid/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("user deleted successfully");
    }

}
