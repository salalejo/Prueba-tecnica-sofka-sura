package com.co.constructores.pruebatecnica.controller;


import com.co.constructores.pruebatecnica.dto.MaterialDTO;
import com.co.constructores.pruebatecnica.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PutMapping("/addmaterial")
    public ResponseEntity<String> updateMaterial(@RequestBody MaterialDTO dto){
        return new ResponseEntity<>(materialService.addMaterial(dto).getMessage(), HttpStatus.OK);
    }
}
