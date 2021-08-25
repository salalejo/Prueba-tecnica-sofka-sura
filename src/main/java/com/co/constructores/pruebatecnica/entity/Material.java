package com.co.constructores.pruebatecnica.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document (collection = "Materials")
@Getter
@Setter
public class Material {
    @Id
    private String id;
    @NonNull
    private String materialName;
    @NonNull
    private int quantity;

    public Material(String id, @NonNull String materialName, int quantity) {
        this.id = id;
        this.materialName = materialName;
        this.quantity = quantity;
    }
}
