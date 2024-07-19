package com.example.EmployeeWeb.common;

import java.util.List;

public interface BaseMapper<Entity, DTO> {

    Entity toEntity(DTO dto);

    DTO toDTO(Entity entity);

    List<DTO> toListDTO(List<Entity> entities);




    }



