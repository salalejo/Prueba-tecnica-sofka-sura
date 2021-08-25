package com.co.constructores.pruebatecnica.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ConstructionDTO {

    private String contructionId;
    private String constructionType;
    private int cementQuantity;
    private int gravelQuantity;
    private int sandQuantity;
    private int woodQuantity;
    private int brickQuantity;
    private int daysToBuild;
    private int xCoordinate;
    private int yCoordinate;

}
