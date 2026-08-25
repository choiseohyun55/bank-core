package com.bank.bank_core.user.controller;

import com.bank.bank_core.user.dto.UserSignupRequest;
import com.bank.bank_core.user.repository.UserRepository;
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
    public ResponseEntity<String> signup(@RequestBody UserSignupRequest request){
        Long userId = userService.signup(request);
        return ResponseEntity.ok("회원가입 성공 (" + userId +")");
    }
}
