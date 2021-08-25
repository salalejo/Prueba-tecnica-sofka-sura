package com.co.constructores.pruebatecnica.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.util.Date;

@Document(collection = "Project")
@Getter
@Setter
public class Project {
    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private Date finishDate;

    public Project(String id, @NonNull String name, @NonNull Date finishDate) {
        this.id = id;
        this.name = name;
        this.finishDate = finishDate;
    }
}
