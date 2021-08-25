package com.co.constructores.pruebatecnica.mapper;

import com.co.constructores.pruebatecnica.dto.MaterialDTO;
import com.co.constructores.pruebatecnica.entity.MaterialEntity;

public class MaterialMapper {

    public MaterialDTO materialEntityToDTO(MaterialEntity materialEntity){
        return MaterialDTO.builder()
                .materialId(materialEntity.getMaterialId())
                .materialName(materialEntity.getMaterialName())
                .quantity(materialEntity.getQuantity())
                .build();
    }

    public MaterialEntity materialDTOToEntity(MaterialDTO materialDTO){
        return MaterialEntity.builder()
                .materialId(materialDTO.getMaterialId())
                .materialName(materialDTO.getMaterialName())
                .quantity(materialDTO.getQuantity())
                .build();
    }
}
