package com.co.constructores.pruebatecnica.mapper;

import com.co.constructores.pruebatecnica.dto.ConstructionDTO;
import com.co.constructores.pruebatecnica.entity.ConstructionEntity;
import org.springframework.stereotype.Component;

@Component
public class ConstructionMapper {

    public ConstructionDTO constructionEntityToDTO(ConstructionEntity constructionEntity){
        return ConstructionDTO.builder()
                .contructionId(constructionEntity.getConstructionId())
                .constructionType(constructionEntity.getConstructionType())
                .cementQuantity(constructionEntity.getCementQuantity())
                .gravelQuantity(constructionEntity.getGravelQuantity())
                .sandQuantity(constructionEntity.getSandQuantity())
                .woodQuantity(constructionEntity.getWoodQuantity())
                .brickQuantity(constructionEntity.getBrickQuantity())
                .daysToBuild(constructionEntity.getDaysToBuild())
                .build();
    }

    public ConstructionEntity constructionDTOToEntity(ConstructionDTO constructionDTO){
        return ConstructionEntity.builder()
                .constructionId(constructionDTO.getContructionId())
                .constructionType(constructionDTO.getConstructionType())
                .cementQuantity(constructionDTO.getCementQuantity())
                .gravelQuantity(constructionDTO.getGravelQuantity())
                .sandQuantity(constructionDTO.getSandQuantity())
                .woodQuantity(constructionDTO.getWoodQuantity())
                .brickQuantity(constructionDTO.getBrickQuantity())
                .daysToBuild(constructionDTO.getDaysToBuild())
                .build();
    }
}
