package com.bank.bank_core.user.controller;

import com.bank.bank_core.auth.dto.TokenResponse;
import com.bank.bank_core.user.dto.UserLoginRequest;
import com.bank.bank_core.user.dto.UserSignupRequest;
import com.bank.bank_core.user.dto.UserSignupResponse;
import com.bank.bank_core.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponse> signup(@RequestBody UserSignupRequest request){
        UserSignupResponse response = userService.signup(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserLoginRequest request){
        TokenResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }

}
