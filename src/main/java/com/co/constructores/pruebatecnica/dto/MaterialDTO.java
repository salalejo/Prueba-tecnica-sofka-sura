package com.co.constructores.pruebatecnica.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MaterialDTO {

    private String materialId;
    private String materialName;
    private int quantity;
}
