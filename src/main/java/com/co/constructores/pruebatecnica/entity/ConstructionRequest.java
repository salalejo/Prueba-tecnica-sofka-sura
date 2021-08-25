package com.co.constructores.pruebatecnica.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document (collection = "ConstructionRequests")
@RequiredArgsConstructor
public class ConstructionRequest {
    @Id
    private String id;
    private String state;
    private Date initialDate;
    private Date finalDate;

}
