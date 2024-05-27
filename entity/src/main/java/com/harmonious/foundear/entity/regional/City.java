package com.harmonious.foundear.entity.regional;

import com.harmonious.foundear.entity.user.Branch;
import com.harmonious.foundear.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "mst_cities", schema = "regional")
public class City {
    @Id
    @Column(name = "city_id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province province;

    @Column(name = "city_code", nullable = false, length = 10)
    private String cityCode;

    @Column(name = "city_name", nullable = false, length = 50)
    private String cityName;

    @Column(name = "is_active", nullable = false)
    private Short isActive;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "last_updated_by", nullable = false)
    private UUID lastUpdatedBy;

    @Column(name = "last_updated_at", nullable = false)
    private Instant lastUpdatedAt;

    @Column(name = "last_approved_by")
    private UUID lastApprovedBy;

    @Column(name = "last_approved_at")
    private Instant lastApprovedAt;

    @Column(name = "last_version_at", nullable = false)
    private Instant lastVersionAt;

    @ColumnDefault("0")
    @Column(name = "is_deleted", nullable = false)
    private Short isDeleted;

    @OneToMany(mappedBy = "city")
    private Set<Branch> branches = new LinkedHashSet<>();

    @OneToMany(mappedBy = "city")
    private Set<User> users = new LinkedHashSet<>();

    @OneToMany(mappedBy = "city")
    private Set<District> districts = new LinkedHashSet<>();

}