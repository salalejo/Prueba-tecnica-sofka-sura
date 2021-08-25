package com.co.constructores.pruebatecnica.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.util.Date;

@Document (collection = "ConstructionRequests")
@Getter
@Setter
public class ConstructionRequest {
    @Id
    private String id;
    @NonNull
    private String state;
    @NonNull
    private Date initialDate;
    @NonNull
    private Date finalDate;
    @NonNull
    private Construction construction;

    public ConstructionRequest(String id, String state, Date initialDate, Date finalDate, Construction construction) {
        this.id = id;
        this.state = state;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.construction = construction;
    }
}
