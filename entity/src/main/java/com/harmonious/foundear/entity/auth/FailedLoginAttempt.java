package com.harmonious.foundear.entity.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "mst_failed_login_attempts", schema = "auth")
public class FailedLoginAttempt {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "attempt_at")
    private Instant attemptAt;

    @Column(name = "log_message")
    private String logMessage;

    @Column(name = "ip_address")
    private String ipAddress;

}