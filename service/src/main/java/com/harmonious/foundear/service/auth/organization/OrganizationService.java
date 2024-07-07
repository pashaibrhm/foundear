package com.harmonious.foundear.service.auth.organization;

import com.harmonious.foundear.dto.auth.organization.OrganizationDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrganizationService {
    List<OrganizationDto> getAllOrganizations();
    Optional<OrganizationDto> getOrganizationById(UUID organizationId);
    OrganizationDto createOrganization(OrganizationDto organizationDto);
    Optional<OrganizationDto> updateOrganization(UUID organizationId, OrganizationDto organizationDto);
    Optional<OrganizationDto> deleteOrganization(UUID organizationId);
    Optional<OrganizationDto> softDeleteOrganization(UUID organizationId);
}
