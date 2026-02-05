package com.danilodps.concurrent.application.controller;

import com.danilodps.concurrent.domain.model.request.create.UserCreateRequest;
import com.danilodps.concurrent.domain.model.request.update.UserUpdateRequest;
import com.danilodps.concurrent.domain.model.response.UserResponse;
import com.danilodps.concurrent.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody UserCreateRequest userCreateRequest){
        return ResponseEntity.ok(userService.create(userCreateRequest));
    }

    @GetMapping("/get")
    public ResponseEntity<UserResponse> get(@RequestParam String userId){
        return ResponseEntity.ok(userService.getInfo(userId));
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestBody UserUpdateRequest userUpdateRequest){
        return ResponseEntity.ok(userService.update(userUpdateRequest));
    }
 }
