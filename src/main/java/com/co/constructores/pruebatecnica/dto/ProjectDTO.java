package com.co.constructores.pruebatecnica.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Setter
@Getter
public class ProjectDTO {

    private String projectId;
    private String name;
    private Date finishDate;
}
