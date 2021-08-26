package com.co.constructores.pruebatecnica.dto;

import com.co.constructores.pruebatecnica.entity.ConstructionEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.Date;

@Builder
@Getter
@Setter
public class ConstructionRequestDTO {

    private String constructionRequestId;
    private String state;
    private Date initialDate;
    private Date finalDate;
    private String constructionType;
    private int xCoordinate;
    private int yCoordinate;
}
