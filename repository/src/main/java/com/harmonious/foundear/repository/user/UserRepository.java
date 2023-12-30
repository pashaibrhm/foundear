package com.harmonious.foundear.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserRepository extends JpaRepository<com.harmonious.foundear.entity.user.User, UUID>, JpaSpecificationExecutor<com.harmonious.foundear.entity.user.User> {
}