package com.alphafolio.backend.user.controller;


import com.alphafolio.backend.config.response.ApiResponseBuilder;
import com.alphafolio.backend.user.dto.UserDTO;
import com.alphafolio.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ApiResponseBuilder.success("Users fetched successfully", users);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ApiResponseBuilder.success("User found", user))
                .orElseGet(() -> ApiResponseBuilder.notFound("User with ID " + id + " not found"));
    }

    @GetMapping("/{username}")
    public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUserName(username)
                .map(user -> ApiResponseBuilder.success("User found", user))
                .orElseGet(() -> ApiResponseBuilder.notFound("User with username '" + username + "' not found"));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO updated = userService.updateUser(userDTO);
        return ApiResponseBuilder.success("User updated successfully", updated);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userService.deleteUserById(id);
//        return ResponseEntity.noContent().build();
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ApiResponseBuilder.success("User deleted successfully");
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Object> changeUserStatus(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        boolean active = body.getOrDefault("active", false);
        UserDTO updated = userService.setUserStatus(id, active);
        return ApiResponseBuilder.success("User status updated successfully", updated);
    }


    @GetMapping("/search")
    public ResponseEntity<Object> searchUsers(@RequestParam String keyword) {
        List<UserDTO> result = userService.searchUsers(keyword);
        return ApiResponseBuilder.success("Search completed", result);
    }
}
