package com.co.constructores.pruebatecnica.controller;

import com.co.constructores.pruebatecnica.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/fechafinal")
    public ResponseEntity<String> getFinishDateProject() {
        return new ResponseEntity<>(projectService.getFinishDate().getMessage(), HttpStatus.OK);
    }

}
