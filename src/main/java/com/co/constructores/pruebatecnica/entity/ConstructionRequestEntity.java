package com.co.constructores.pruebatecnica.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.util.Date;

@Document (collection = "ConstructionRequests")
@Builder
@Getter
@Setter
public class ConstructionRequestEntity {
    @Id
    private String constructionRequestId;
    private String state;
    private String initialDate;
    private String finalDate;
    private String constructionType;
    private int coordinateX;
    private int coordinateY;


}
