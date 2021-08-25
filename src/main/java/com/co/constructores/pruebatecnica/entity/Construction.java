package com.co.constructores.pruebatecnica.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document (collection = "Constructions")
@Getter
@Setter
public class Construction {
    @Id
    private String id;
    @NonNull
    private String constructionType;
    @NonNull
    private int cementQuantity;
    @NonNull
    private int gravelQuantity;
    @NonNull
    private int sandQuantity;
    @NonNull
    private int woodQuantity;
    @NonNull
    private int brickQuantity;
    @NonNull
    private int daysToBuild;
    @NonNull
    private int xCoordinate;
    @NonNull
    private int yCoordinate;

    public Construction(String id, @NonNull String constructionType, int cementQuantity, int gravelQuantity, int sandQuantity, int woodQuantity, int brickQuantity, int daysToBuild, int xCoordinate, int yCoordinate) {
        this.id = id;
        this.constructionType = constructionType;
        this.cementQuantity = cementQuantity;
        this.gravelQuantity = gravelQuantity;
        this.sandQuantity = sandQuantity;
        this.woodQuantity = woodQuantity;
        this.brickQuantity = brickQuantity;
        this.daysToBuild = daysToBuild;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
}
