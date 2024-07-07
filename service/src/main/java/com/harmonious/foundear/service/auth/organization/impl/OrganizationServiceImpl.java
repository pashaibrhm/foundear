package com.harmonious.foundear.service.auth.organization.impl;

import com.harmonious.foundear.dto.auth.organization.OrganizationDto;
import com.harmonious.foundear.entity.auth.Organization;
import com.harmonious.foundear.mapper.auth.organization.OrganizationMapper;
import com.harmonious.foundear.repository.auth.organization.OrganizationRepository;
import com.harmonious.foundear.service.auth.organization.OrganizationService;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Getter
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    private final Logger logger;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper, Logger logger) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
        this.logger = logger;
    }

    private void logInfo(UUID organizationId) {
        logger.info("Fetching organization by ID: {}", organizationId);
    }

    @Override
    public List<OrganizationDto> getAllOrganizations() {
        logger.info("Fetching all organizations");
        List<Organization> organizations = organizationRepository.findAll();
        return organizationMapper.toDtos(organizations);
    }

    @Override
    public Optional<OrganizationDto> getOrganizationById(UUID organizationId) {
        logInfo(organizationId);
        Optional<Organization> optionalOrganization = organizationRepository.findById(organizationId);
        if (optionalOrganization.isEmpty()) {
            logger.warn("Organization with ID {} not found", organizationId);
            return Optional.empty();
        }
        return optionalOrganization.map(organizationMapper::toDto);
    }

    @Override
    public OrganizationDto createOrganization(OrganizationDto organizationDto) {
        logger.info("Creating organization: {}", organizationDto);
        return organizationMapper.toDto(organizationRepository.save(organizationMapper.toEntity(organizationDto)));
    }

    @Override
    public Optional<OrganizationDto> updateOrganization(UUID organizationId, OrganizationDto organizationDto) {
        Objects.requireNonNull(organizationDto, "OrganizationDTO cannot be null");

        if (!Objects.equals(organizationId, organizationDto.getId())) {
            throw new IllegalArgumentException("Organization with ID " + organizationId + " and ID " + organizationDto.getId() + " does not match");
        }

        logInfo(organizationId);
        Optional<Organization> optionalOrganization = organizationRepository.findById(organizationId);

        if (optionalOrganization.isPresent()) {
            Organization exisitingOrganization = organizationMapper.toEntity(organizationDto);

            logger.info("Updating organization: {}", organizationDto);
            Organization updatedOrganization = organizationRepository.save(exisitingOrganization);
            return Optional.of(organizationMapper.toDto(updatedOrganization));
        } else {
            logger.warn("Organization with ID {} not found for update", organizationId);
            return Optional.empty();
        }
    }

    @Override
    public Optional<OrganizationDto> deleteOrganization(UUID organizationId) {
        logger.info("Deleting organization: {}", organizationId);
        Optional<Organization> optionalOrganization = organizationRepository.findById(organizationId);
        if (optionalOrganization.isPresent()) {
            Organization organization = optionalOrganization.get();
            organizationRepository.delete(organization);
            return Optional.of(organizationMapper.toDto(organization));
        }
        return Optional.empty();
    }

    @Override
    public Optional<OrganizationDto> softDeleteOrganization(UUID organizationId) {
        try {
            return Optional.of(organizationRepository.findById(organizationId)
                    .map(organization -> {
                        organization.setIsDeleted((short) 1);
                        logger.info("Soft deleting organization: {}", organizationId);
                        return organization;
                    })
                    .orElseThrow(() -> {
                        logger.warn("Organization with ID {} not found for soft delete", organizationId);
                        return new NoSuchElementException("Organization with ID " + organizationId + " not found.");
                    })).map(organizationMapper::toDto);
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }
}
