package com.co.constructores.pruebatecnica.mapper;

import com.co.constructores.pruebatecnica.dto.ProjectDTO;
import com.co.constructores.pruebatecnica.entity.ProjectEntity;

public class ProjectMapper {

    public ProjectDTO projectEntityToDTO(ProjectEntity projectEntity){
        return ProjectDTO.builder()
                .projectId(projectEntity.getId())
                .name(projectEntity.getName())
                .finishDate(projectEntity.getFinishDate())
                .build();
    }

    public ProjectEntity projectDTOToEntity(ProjectDTO projectDTO){
        return ProjectEntity.builder()
                .id(projectDTO.getProjectId())
                .name(projectDTO.getName())
                .finishDate(projectDTO.getFinishDate())
                .build();
    }
}
