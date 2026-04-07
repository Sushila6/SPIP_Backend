package com.example.smartplot.service;

import com.example.smartplot.dto.AuthResponse;
import com.example.smartplot.dto.ChangePasswordRequest;
import com.example.smartplot.dto.LoginRequest;
import com.example.smartplot.dto.MessageResponse;
import com.example.smartplot.dto.OtpLoginRequest;
import com.example.smartplot.dto.OtpVerifyRequest;
import com.example.smartplot.dto.ProfileUpdateRequest;
import com.example.smartplot.dto.ResetPasswordWithOtpRequest;
import com.example.smartplot.dto.SignupRequest;
import com.example.smartplot.dto.SignupOtpVerifyRequest;
import com.example.smartplot.dto.UserResponse;
import com.example.smartplot.model.PendingSignup;
import com.example.smartplot.model.User;
import com.example.smartplot.repository.PendingSignupRepository;
import com.example.smartplot.repository.UserRepository;
import com.example.smartplot.security.CustomUserDetailsService;
import com.example.smartplot.security.JwtService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final NotificationService notificationService;
    private final PendingSignupRepository pendingSignupRepository;
    private final EmailOtpService emailOtpService;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            CustomUserDetailsService customUserDetailsService,
            NotificationService notificationService,
            PendingSignupRepository pendingSignupRepository,
            EmailOtpService emailOtpService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
        this.notificationService = notificationService;
        this.pendingSignupRepository = pendingSignupRepository;
        this.emailOtpService = emailOtpService;
    }

    public AuthResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        String role = normalizeRole(request.getRole());

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);
        notificationService.createNotification(
                savedUser.getEmail(),
                "Welcome to SmartPlot",
                "Your account has been created successfully as " + savedUser.getRole() + ".",
                "ACCOUNT"
        );
        UserResponse userResponse = mapToResponse(savedUser);
        String token = jwtService.generateToken(savedUser.getEmail());
        return new AuthResponse("Signup successful", token, userResponse);
    }

    public MessageResponse requestSignupOtp(SignupRequest request) {
        String normalizedEmail = request.getEmail().trim().toLowerCase();
        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("Email is already registered");
        }

        String role = normalizeRole(request.getRole());
        String otpCode = generateOtp();

        PendingSignup pendingSignup = pendingSignupRepository.findByEmail(normalizedEmail)
                .orElseGet(PendingSignup::new);
        pendingSignup.setName(request.getName().trim());
        pendingSignup.setEmail(normalizedEmail);
        pendingSignup.setPhone(request.getPhone().trim());
        pendingSignup.setRole(role);
        pendingSignup.setPassword(passwordEncoder.encode(request.getPassword()));
        pendingSignup.setOtpCode(otpCode);
        pendingSignup.setOtpExpiresAt(LocalDateTime.now().plusMinutes(5));
        pendingSignupRepository.save(pendingSignup);

        emailOtpService.sendOtpEmail(
                normalizedEmail,
                "Your SmartPlot signup OTP",
                "Welcome to SmartPlot.",
                otpCode
        );

        return new MessageResponse("Signup OTP sent successfully. Check your email for the 6-digit code.");
    }

    public AuthResponse verifySignupOtp(SignupOtpVerifyRequest request) {
        PendingSignup pendingSignup = pendingSignupRepository.findByEmail(request.getEmail().trim().toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("No pending signup found for this email"));

        String requestedRole = normalizeRole(request.getRole());
        if (!requestedRole.equalsIgnoreCase(pendingSignup.getRole())) {
            throw new IllegalArgumentException("Invalid email, role, or OTP");
        }

        validatePendingSignupOtp(pendingSignup, request.getOtpCode());

        if (userRepository.existsByEmail(pendingSignup.getEmail())) {
            pendingSignupRepository.delete(pendingSignup);
            throw new IllegalArgumentException("Email is already registered");
        }

        User user = new User();
        user.setName(pendingSignup.getName());
        user.setEmail(pendingSignup.getEmail());
        user.setPhone(pendingSignup.getPhone());
        user.setRole(pendingSignup.getRole());
        user.setPassword(pendingSignup.getPassword());

        User savedUser = userRepository.save(user);
        pendingSignupRepository.delete(pendingSignup);
        notificationService.createNotification(
                savedUser.getEmail(),
                "Welcome to SmartPlot",
                "Your account has been created successfully as " + savedUser.getRole() + ".",
                "ACCOUNT"
        );
        String token = jwtService.generateToken(savedUser.getEmail());
        return new AuthResponse("Signup successful", token, mapToResponse(savedUser));
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email, password, or role"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email, password, or role");
        }

        String requestedRole = normalizeRole(request.getRole());
        String actualRole = normalizeRole(user.getRole());

        if (!actualRole.equals(requestedRole)) {
            throw new IllegalArgumentException("Invalid email, password, or role");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse("Login successful", token, mapToResponse(user));
    }

    public UserResponse getCurrentUser(String userEmail) {
        return mapToResponse(getUserByEmail(userEmail));
    }

    public UserResponse updateProfile(String userEmail, ProfileUpdateRequest request) {
        User user = getUserByEmail(userEmail);
        user.setName(request.getName().trim());
        user.setPhone(request.getPhone().trim());
        User savedUser = userRepository.save(user);
        customUserDetailsService.evictUser(savedUser.getEmail());
        notificationService.createNotification(
                savedUser.getEmail(),
                "Profile updated",
                "Your profile details were updated successfully.",
                "ACCOUNT"
        );
        return mapToResponse(savedUser);
    }

    public MessageResponse changePassword(String userEmail, ChangePasswordRequest request) {
        User user = getUserByEmail(userEmail);

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        customUserDetailsService.evictUser(user.getEmail());
        notificationService.createNotification(
                user.getEmail(),
                "Password changed",
                "Your password was changed from the profile security section.",
                "SECURITY"
        );
        return new MessageResponse("Password updated successfully");
    }

    public MessageResponse requestOtp(OtpLoginRequest request) {
        User user = validateOtpUser(request.getEmail(), request.getRole());
        String otpCode = generateOtp();
        user.setOtpCode(otpCode);
        user.setOtpRole(normalizeRole(request.getRole()));
        user.setOtpExpiresAt(LocalDateTime.now().plusMinutes(5));
        userRepository.save(user);
        emailOtpService.sendOtpEmail(
                user.getEmail(),
                "Your SmartPlot login OTP",
                "Use this code to sign in to SmartPlot.",
                otpCode
        );
        return new MessageResponse("OTP sent successfully. Check your email for the 6-digit code.");
    }

    public MessageResponse requestPasswordResetOtp(OtpLoginRequest request) {
        User user = validateOtpUser(request.getEmail(), request.getRole());
        String otpCode = generateOtp();
        user.setOtpCode(otpCode);
        user.setOtpRole(normalizeRole(request.getRole()));
        user.setOtpExpiresAt(LocalDateTime.now().plusMinutes(5));
        userRepository.save(user);
        emailOtpService.sendOtpEmail(
                user.getEmail(),
                "Your SmartPlot password reset OTP",
                "Use this code to reset your SmartPlot password.",
                otpCode
        );
        return new MessageResponse("Reset OTP sent successfully. Check your email for the 6-digit code.");
    }

    public MessageResponse resetPasswordWithOtp(ResetPasswordWithOtpRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email, role, or OTP"));

        String requestedRole = normalizeRole(request.getRole());
        String actualRole = normalizeRole(user.getRole());

        if (!actualRole.equals(requestedRole)) {
            throw new IllegalArgumentException("Invalid email, role, or OTP");
        }

        validateOtpForUser(user, requestedRole, request.getOtpCode());
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        clearOtp(user);
        userRepository.save(user);
        customUserDetailsService.evictUser(user.getEmail());
        notificationService.createNotification(
                user.getEmail(),
                "Password reset",
                "Your password was reset successfully using OTP verification.",
                "SECURITY"
        );
        return new MessageResponse("Password reset successful. You can now log in with the new password");
    }

    private User validateOtpUser(String email, String role) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("No account found for this email"));

        String requestedRole = normalizeRole(role);
        String actualRole = normalizeRole(user.getRole());

        if (!actualRole.equals(requestedRole)) {
            throw new IllegalArgumentException("Invalid role for this account");
        }

        return user;
    }

    public AuthResponse verifyOtp(OtpVerifyRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email, role, or OTP"));

        String requestedRole = normalizeRole(request.getRole());
        String actualRole = normalizeRole(user.getRole());

        if (!actualRole.equals(requestedRole)) {
            throw new IllegalArgumentException("Invalid email, role, or OTP");
        }

        validateOtpForUser(user, requestedRole, request.getOtpCode());
        clearOtp(user);
        userRepository.save(user);
        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse("OTP login successful", token, mapToResponse(user));
    }

    private void validateOtpForUser(User user, String requestedRole, String otpCode) {
        if (user.getOtpCode() == null || user.getOtpExpiresAt() == null || user.getOtpRole() == null) {
            throw new IllegalArgumentException("OTP has not been requested yet");
        }

        if (!requestedRole.equalsIgnoreCase(user.getOtpRole())) {
            throw new IllegalArgumentException("OTP role mismatch");
        }

        if (user.getOtpExpiresAt().isBefore(LocalDateTime.now())) {
            clearOtp(user);
            userRepository.save(user);
            throw new IllegalArgumentException("OTP has expired. Request a new one");
        }

        if (!otpCode.trim().equals(user.getOtpCode())) {
            throw new IllegalArgumentException("Invalid email, role, or OTP");
        }
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole()
        );
    }

    private User getUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    private void clearOtp(User user) {
        user.setOtpCode(null);
        user.setOtpExpiresAt(null);
        user.setOtpRole(null);
    }

    private void validatePendingSignupOtp(PendingSignup pendingSignup, String otpCode) {
        if (pendingSignup.getOtpExpiresAt() == null || pendingSignup.getOtpCode() == null) {
            throw new IllegalArgumentException("Signup OTP has not been requested yet");
        }

        if (pendingSignup.getOtpExpiresAt().isBefore(LocalDateTime.now())) {
            pendingSignupRepository.delete(pendingSignup);
            throw new IllegalArgumentException("OTP has expired. Request a new signup OTP");
        }

        if (!otpCode.trim().equals(pendingSignup.getOtpCode())) {
            throw new IllegalArgumentException("Invalid email, role, or OTP");
        }
    }

    private String generateOtp() {
        int otp = ThreadLocalRandom.current().nextInt(100000, 1000000);
        return String.valueOf(otp);
    }

    private String normalizeRole(String role) {
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("Role is required");
        }

        String normalized = role.trim().toUpperCase();

        if (!normalized.equals("USER") && !normalized.equals("ADMIN") && !normalized.equals("INVESTOR")) {
            throw new IllegalArgumentException("Role must be USER, ADMIN, or INVESTOR");
        }

        return normalized;
    }
}
