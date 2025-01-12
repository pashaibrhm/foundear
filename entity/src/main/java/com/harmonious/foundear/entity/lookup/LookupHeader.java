package com.harmonious.foundear.entity.lookup;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "mst_lookup_headers", schema = "lookup")
public class LookupHeader {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "value", length = 20)
    private String value;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "last_updated_at")
    private Instant lastUpdatedAt;

    @Column(name = "last_updated_by")
    private UUID lastUpdatedBy;

    @Column(name = "last_approved_at")
    private Instant lastApprovedAt;

    @Column(name = "last_approved_by")
    private UUID lastApprovedBy;

    @Column(name = "last_version_at")
    private Instant lastVersionAt;

    @ColumnDefault("0")
    @Column(name = "is_deleted")
    private Short isDeleted;

    @OneToMany(mappedBy = "header")
    private Set<LookupDetail> lookupDetails = new LinkedHashSet<>();

}