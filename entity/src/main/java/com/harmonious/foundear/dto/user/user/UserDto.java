package com.harmonious.foundear.dto.user.user;

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
    private UUID id;

    private transient City city;
    private transient District district;
    private transient Village village;
    private transient Country country;
    private transient Province province;
    private transient Group group;

    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String addressDetail;
    private BigDecimal lockCount;

    private Instant createdAt;
    private UUID createdBy;
    private UUID approvedBy;
    private Instant approvedAt;
    private UUID lastUpdatedBy;
    private Instant lastUpdatedAt;
    private Instant lastVersionAt;

    @Builder.Default
    private Short isDeleted = 0;
    @Builder.Default
    private Short isLocked = 0;
    @Builder.Default
    private transient Set<FailedLoginAttempt> failedLoginAttempts = new LinkedHashSet<>();
    @Builder.Default
    private transient Set<UserSession> userSessions = new LinkedHashSet<>();

    public static UserDto createDummyUserDto() {
        return UserDto.builder()
                .id(UUID.randomUUID())
                .email("dummy@dummy")
                .firstName("FirstDummy")
                .middleName("MiddleDummy")
                .lastName("LastDummy")
                .username("dummy")
                .addressDetail("Dummy")
                .lockCount(BigDecimal.ZERO)
                .createdAt(Instant.now())
                .createdBy(UUID.randomUUID())
                .approvedBy(UUID.randomUUID())
                .approvedAt(Instant.now())
                .lastUpdatedBy(UUID.randomUUID())
                .lastUpdatedAt(Instant.now())
                .lastVersionAt(Instant.now())
                .isLocked((short) 0)
                .city(City.builder().id(UUID.randomUUID()).build())
                .district(District.builder().id(UUID.randomUUID()).build())
                .village(Village.builder().id(UUID.randomUUID()).build())
                .country(Country.builder().id(UUID.randomUUID()).build())
                .province(Province.builder().id(UUID.randomUUID()).build())
                .group(Group.builder().id(UUID.randomUUID()).build())
                .failedLoginAttempts(new LinkedHashSet<>())
                .userSessions(new LinkedHashSet<>())
                .password("dummy")
                .isDeleted((short) 0)
                .build();
    }
}