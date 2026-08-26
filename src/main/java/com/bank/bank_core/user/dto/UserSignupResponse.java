package com.bank.bank_core.user.dto;

import com.bank.bank_core.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserSignupResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createAt;

    public static UserSignupResponse from(User user) {
        return new UserSignupResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getCreateAt()
        );
    }
}
