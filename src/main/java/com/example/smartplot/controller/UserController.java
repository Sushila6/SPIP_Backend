package com.example.smartplot.controller;

import com.example.smartplot.dto.*;
import com.example.smartplot.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ── Signup ──────────────────────────────────────────────
    @PostMapping("/signup/request-otp")
    public MessageResponse requestSignupOtp(@Valid @RequestBody SignupRequest request) {
        return userService.requestSignupOtp(request);
    }

    @PostMapping("/signup/verify-otp")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse verifySignupOtp(@Valid @RequestBody SignupOtpVerifyRequest request) {
        return userService.verifySignupOtp(request);
    }

    // ── Login ────────────────────────────────────────────────
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/login/request-otp")
    public MessageResponse requestOtp(@Valid @RequestBody OtpLoginRequest request) {
        return userService.requestOtp(request);
    }

    @PostMapping("/login/verify-otp")
    public AuthResponse verifyOtp(@Valid @RequestBody OtpVerifyRequest request) {
        return userService.verifyOtp(request);
    }

    // ── Password reset ───────────────────────────────────────
    @PostMapping("/login/request-password-reset")
    public MessageResponse requestPasswordReset(@Valid @RequestBody OtpLoginRequest request) {
        return userService.requestPasswordResetOtp(request);
    }

    @PostMapping("/login/reset-password")
    public MessageResponse resetPassword(@Valid @RequestBody ResetPasswordWithOtpRequest request) {
        return userService.resetPasswordWithOtp(request);
    }

    // ── Profile (requires JWT) ───────────────────────────────
    @GetMapping("/me")
    public UserResponse getMe(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getCurrentUser(userDetails.getUsername());
    }

    @PutMapping("/me")
    public UserResponse updateMe(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody ProfileUpdateRequest request) {
        return userService.updateProfile(userDetails.getUsername(), request);
    }

    @PostMapping("/me/change-password")
    public MessageResponse changePassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody ChangePasswordRequest request) {
        return userService.changePassword(userDetails.getUsername(), request);
    }

    // ── Admin ────────────────────────────────────────────────
    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}