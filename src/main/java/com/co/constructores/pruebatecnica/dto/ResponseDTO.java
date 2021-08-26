package com.co.constructores.pruebatecnica.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {

    private String message;

    public ResponseDTO(String message) {
        this.message = message;
    }
}
