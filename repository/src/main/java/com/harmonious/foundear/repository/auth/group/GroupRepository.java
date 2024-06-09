package com.harmonious.foundear.repository.auth.group;

import com.harmonious.foundear.entity.auth.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID>, JpaSpecificationExecutor<Group> {
}
