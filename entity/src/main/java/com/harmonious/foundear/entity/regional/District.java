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
@Table(name = "mst_districts", schema = "regional")
public class District {
    @Id
    @Column(name = "district_id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "district_code", nullable = false, length = 10)
    private String districtCode;

    @Column(name = "district_name", nullable = false, length = 50)
    private String districtName;

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

    @OneToMany(mappedBy = "district")
    private Set<Branch> branches = new LinkedHashSet<>();

    @OneToMany(mappedBy = "district")
    private Set<User> users = new LinkedHashSet<>();

    @OneToMany(mappedBy = "district")
    private Set<Village> villages = new LinkedHashSet<>();

}