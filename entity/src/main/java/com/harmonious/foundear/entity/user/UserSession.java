package com.harmonious.foundear.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "mst_user_sessions", schema = "foundear")
public class UserSession {
    @Id
    @Column(name = "session_id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "user_agent", nullable = false)
    private String userAgent;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

}