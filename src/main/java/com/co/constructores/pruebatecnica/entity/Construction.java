package com.co.constructores.pruebatecnica.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Constructions")
@RequiredArgsConstructor
public class Construction {
    @Id
    private String id;
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
