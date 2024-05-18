package com.harmonious.foundear.dto.user;

import com.harmonious.foundear.entity.regional.*;
import com.harmonious.foundear.entity.user.FailedLoginAttempt;
import com.harmonious.foundear.entity.user.Group;
import com.harmonious.foundear.entity.user.UserSession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.harmonious.foundear.entity.user.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class UserDto implements Serializable {
    private UUID userId;
    private Instant createdAt;
    private UUID createdBy;
    private UUID approvedBy;
    private Boolean isDeleted = false;
    private City city;
    private District district;
    private Village village;
    private Country country;
    private Instant approvedAt;
    private Province province;
    private Group group;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String addressDetail;
    private UUID lastUpdatedBy;
    private Instant lastUpdatedAt;
    private Instant lastVersionAt;
    private BigDecimal lockCount;
    private Short isLocked;
    private Set<FailedLoginAttempt> failedLoginAttempts = new LinkedHashSet<>();
    private Set<UserSession> userSessions = new LinkedHashSet<>();
}