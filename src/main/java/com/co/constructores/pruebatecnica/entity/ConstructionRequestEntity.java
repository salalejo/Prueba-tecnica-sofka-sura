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
    @NonNull
    private String state;
    @NonNull
    private Date initialDate;
    @NonNull
    private Date finalDate;
    @NonNull
    private ConstructionEntity constructionEntity;

}
