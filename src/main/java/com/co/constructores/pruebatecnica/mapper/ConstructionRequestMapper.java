package com.co.constructores.pruebatecnica.mapper;

import com.co.constructores.pruebatecnica.dto.ConstructionRequestDTO;
import com.co.constructores.pruebatecnica.entity.ConstructionRequestEntity;

public class ConstructionRequestMapper {
    public ConstructionRequestDTO constructionRequestEntityToDTO(ConstructionRequestEntity constructionRequestEntity){
        return ConstructionRequestDTO.builder()
                .constructionRequestId(constructionRequestEntity.getConstructionRequestId())
                .state(constructionRequestEntity.getState())
                .initialDate(constructionRequestEntity.getInitialDate())
                .finalDate(constructionRequestEntity.getFinalDate())
                .constructionEntity(constructionRequestEntity.getConstructionEntity())
                .build();
    }

    public ConstructionRequestEntity constructionRequestDTOToEntity(ConstructionRequestDTO constructionRequestDTO){
        return ConstructionRequestEntity.builder()
                .constructionRequestId(constructionRequestDTO.getConstructionRequestId())
                .state(constructionRequestDTO.getState())
                .initialDate(constructionRequestDTO.getInitialDate())
                .finalDate(constructionRequestDTO.getFinalDate())
                .constructionEntity(constructionRequestDTO.getConstructionEntity())
                .build();
    }
}
