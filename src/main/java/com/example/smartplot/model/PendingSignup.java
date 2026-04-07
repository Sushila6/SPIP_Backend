package com.example.smartplot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "pending_signups")
public class PendingSignup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pending_signup_id")
    private Integer pendingSignupId;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "phone", length = 15, nullable = false)
    private String phone;

    @Column(name = "role", length = 20, nullable = false)
    private String role;

    @Column(name = "otp_code", length = 10, nullable = false)
    private String otpCode;

    @Column(name = "otp_expires_at", nullable = false)
    private LocalDateTime otpExpiresAt;

    public Integer getPendingSignupId() {
        return pendingSignupId;
    }

    public void setPendingSignupId(Integer pendingSignupId) {
        this.pendingSignupId = pendingSignupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public LocalDateTime getOtpExpiresAt() {
        return otpExpiresAt;
    }

    public void setOtpExpiresAt(LocalDateTime otpExpiresAt) {
        this.otpExpiresAt = otpExpiresAt;
    }
}
