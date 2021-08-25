package com.co.constructores.pruebatecnica.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document (collection = "Constructions")
@Builder
@Getter
@Setter
public class ConstructionEntity {
    @Id
    private String constructionId;
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

}
