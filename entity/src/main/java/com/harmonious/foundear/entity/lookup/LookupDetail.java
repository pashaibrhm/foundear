package com.harmonious.foundear.entity.lookup;

import com.harmonious.foundear.entity.auth.Branch;
import com.harmonious.foundear.entity.foundear.EntityFile;
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
@Table(name = "mst_lookup_details", schema = "lookup")
public class LookupDetail {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lookup_header_id", nullable = false)
    private LookupHeader header;

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

    @OneToMany(mappedBy = "branchType")
    private Set<Branch> branchTypes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "entityType")
    private Set<Branch> entityTypes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "lookupEntityType")
    private Set<EntityFile> entityFiles = new LinkedHashSet<>();

}