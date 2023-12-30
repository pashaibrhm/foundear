package com.harmonious.foundear.helper;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityDtoConverterHelper {

    // Convert a single DTO to an entity
    public static <D, E> E convertToEntity(D dto, Class<E> entityClass) {
        E entity;
        try {
            entity = entityClass.getDeclaredConstructor().newInstance();
            copyProperties(dto, entity);
        } catch (Exception e) {
            throw new RuntimeException("Error creating entity instance", e);
        }
        return entity;
    }

    // Convert a single entity to a DTO
    public static <D, E> D convertToDto(E entity, Class<D> dtoClass) {
        D dto;
        try {
            dto = dtoClass.getDeclaredConstructor().newInstance();
            copyProperties(entity, dto);
        } catch (Exception e) {
            throw new RuntimeException("Error creating DTO instance", e);
        }
        return dto;
    }

    // Convert a list of DTOs to a list of entities
    public static <D, E> List<E> convertListToEntityList(List<D> dtoList, Class<E> entityClass) {
        return dtoList.stream()
                .map(dto -> convertToEntity(dto, entityClass))
                .collect(Collectors.toList());
    }

    // Convert a list of entities to a list of DTOs
    public static <D, E> List<D> convertEntityListToList(List<E> entityList, Class<D> dtoClass) {
        return entityList.stream()
                .map(entity -> convertToDto(entity, dtoClass))
                .collect(Collectors.toList());
    }

    // Helper method to copy properties between objects using Reflection
    public static void copyProperties(Object source, Object destination) {
        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] destinationFields = destination.getClass().getDeclaredFields();

        for (Field sourceField : sourceFields) {
            for (Field destinationField : destinationFields) {
                if (sourceField.getName().equals(destinationField.getName())) {
                    try {
                        sourceField.setAccessible(true);
                        destinationField.setAccessible(true);
                        destinationField.set(destination, sourceField.get(source));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Error copying properties", e);
                    }
                    break;
                }
            }
        }
    }
}
