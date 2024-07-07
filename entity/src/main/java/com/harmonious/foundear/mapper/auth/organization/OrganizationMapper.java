package com.harmonious.foundear.mapper.auth.organization;

import com.harmonious.foundear.dto.auth.organization.OrganizationDto;
import com.harmonious.foundear.entity.auth.Organization;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrganizationMapper {
    Organization toEntity(OrganizationDto organizationDto);

    @AfterMapping
    default void linkGroups(@MappingTarget Organization organization) {
        organization.getGroups().forEach(group -> group.setOrg(organization));
    }

    OrganizationDto toDto(Organization organization);

    List<OrganizationDto> toDtos(List<Organization> organizations);

    List<Organization> toEntities(List<OrganizationDto> organizationDtos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Organization partialUpdate(OrganizationDto organizationDto,
                               @MappingTarget Organization organization);
}