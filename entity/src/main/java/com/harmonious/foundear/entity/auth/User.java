package com.harmonious.foundear.entity.auth;

import com.harmonious.foundear.config.CustomUuidGenerator;
import com.harmonious.foundear.entity.regional.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "mst_user", schema = "auth")
public class User {
    @Id
    @GeneratedValue(generator = "custom-uuid")
    @GenericGenerator(name = "custom-uuid", type = CustomUuidGenerator.class)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "approved_at")
    private Instant approvedAt;

    @Column(name = "approved_by")
    private UUID approvedBy;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Short isDeleted = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "village_id")
    private Village village;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "middle_name", length = 20)
    private String middleName;

    @Column(name = "last_name", length = 20)
    private String lastName;

    @Column(name = "username", nullable = false, length = 30)
    private String username;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address_detail")
    private String addressDetail;

    @Column(name = "last_updated_by", nullable = false)
    private UUID lastUpdatedBy;

    @Column(name = "last_updated_at", nullable = false)
    private Instant lastUpdatedAt;

    @Column(name = "last_version_at", nullable = false)
    private Instant lastVersionAt;

    @ColumnDefault("0")
    @Column(name = "lock_count", precision = 1)
    private BigDecimal lockCount;

    @ColumnDefault("0")
    @Column(name = "is_locked")
    private Short isLocked;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private Set<FailedLoginAttempt> failedLoginAttempts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private Set<UserSession> userSessions = new LinkedHashSet<>();

    // In your User service or a separate utility class
    public static User createDummyUser() {
        return User.builder()
                .id(UUID.randomUUID())
                .email("dummy@dummy")
                .password("dummypass")
                .isDeleted((short) 0)
                .city(City.builder().id(UUID.randomUUID()).build())
                .district(District.builder().id(UUID.randomUUID()).build())
                .village(Village.builder().id(UUID.randomUUID()).build())
                .country(Country.builder().id(UUID.randomUUID()).build())
                .province(Province.builder().id(UUID.randomUUID()).build())
                .group(Group.builder().id(UUID.randomUUID()).build())
                .firstName("FirstDummy")
                .middleName("MiddleDummy")
                .lastName("LastDummy")
                .username("dummy")
                .addressDetail("Dummy")
                .approvedAt(Instant.now())
                .lastUpdatedBy(UUID.randomUUID())
                .lastUpdatedAt(Instant.now())
                .lastVersionAt(Instant.now())
                .lockCount(BigDecimal.ZERO)
                .isLocked((short) 0)
                .failedLoginAttempts(new LinkedHashSet<>())
                .userSessions(new LinkedHashSet<>())
                .build();
    }
}