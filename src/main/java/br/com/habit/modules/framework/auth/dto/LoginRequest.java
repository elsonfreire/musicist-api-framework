package br.com.habit.modules.framework.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
    @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email should have a maximum of 100 characters")
        String email,
    @NotBlank(message = "Password is required")
        @Size(min = 6, max = 100, message = "Password should be between 6 and 100 characters")
        String password) {}
