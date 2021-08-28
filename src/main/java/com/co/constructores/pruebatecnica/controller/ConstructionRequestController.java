package com.co.constructores.pruebatecnica.controller;

import com.co.constructores.pruebatecnica.dto.ConstructionRequestDTO;
import com.co.constructores.pruebatecnica.dto.ResponseDTO;
import com.co.constructores.pruebatecnica.service.ConstructionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api")
public class ConstructionRequestController {

    @Autowired
    private ConstructionRequestService constructionRequestService;

    @PostMapping("/createcr")
    public ResponseEntity<String> createConstructionRequest(@RequestBody ConstructionRequestDTO dto) throws ParseException {
        return new ResponseEntity<>(constructionRequestService.createConstructionRequest(dto).getMessage(), HttpStatus.OK);
    }

    @GetMapping("/informe")
    public ResponseEntity<String> getReport() {
        return new ResponseEntity<>(constructionRequestService.createReport().getMessage(), HttpStatus.OK);
    }


}
