package com.co.constructores.pruebatecnica.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Materials")
@RequiredArgsConstructor
public class Material {
    @Id
    private String id;
    private String materialName;
    private int quantity;
}
