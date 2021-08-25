package com.co.constructores.pruebatecnica.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document (collection = "Materials")
@Builder
@Getter
@Setter
public class MaterialEntity {
    @Id
    private String materialId;
    @NonNull
    private String materialName;
    @NonNull
    private int quantity;

}
