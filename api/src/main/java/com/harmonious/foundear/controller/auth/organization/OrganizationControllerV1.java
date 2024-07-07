package com.harmonious.foundear.controller.auth.organization;

import com.harmonious.foundear.dto.auth.organization.OrganizationDto;
import com.harmonious.foundear.service.auth.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationControllerV1 {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationControllerV1(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations() {
        List<OrganizationDto> organizations = organizationService.getAllOrganizations();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @GetMapping("/{organizationId}")
    public ResponseEntity<OrganizationDto> getOrganizationById(@PathVariable UUID organizationId) {
        try {
            Optional<OrganizationDto> organizationDto = organizationService.getOrganizationById(organizationId);

            return organizationDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> createOrganization(@RequestBody OrganizationDto organizationDto) {
        OrganizationDto createdOrganization = organizationService.createOrganization(organizationDto);
        return new ResponseEntity<>(createdOrganization, HttpStatus.CREATED);
    }

    @PutMapping("/{organizationId}")
    public ResponseEntity<Optional<OrganizationDto>> updateOrganization(@PathVariable UUID organizationId, @RequestBody OrganizationDto organizationDto) {
        try {
            Optional<OrganizationDto> updatedOrganization = organizationService.updateOrganization(organizationId, organizationDto);
            return new ResponseEntity<>(updatedOrganization, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{organizationId}")
    public ResponseEntity<Optional<OrganizationDto>> deleteOrganization(@PathVariable UUID organizationId) {
        Optional<OrganizationDto> existingOrganizationOptional = organizationService.getOrganizationById(organizationId);

        if (existingOrganizationOptional.isPresent()) {
            try {
                Optional<OrganizationDto> deletedDtoOptional = organizationService.softDeleteOrganization(organizationId);
                if (deletedDtoOptional.isPresent()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
