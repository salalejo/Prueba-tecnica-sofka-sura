package com.co.constructores.pruebatecnica.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.util.Date;

@Document(collection = "Project")
@Builder
@Getter
@Setter
public class ProjectEntity {
    @Id
    private String projectId;
    @NonNull
    private String name;
    @NonNull
    private Date finishDate;

}
