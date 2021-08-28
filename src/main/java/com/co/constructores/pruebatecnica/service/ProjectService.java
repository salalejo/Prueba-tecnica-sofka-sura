package com.co.constructores.pruebatecnica.service;


import com.co.constructores.pruebatecnica.dto.ResponseDTO;
import com.co.constructores.pruebatecnica.mapper.ProjectMapper;
import com.co.constructores.pruebatecnica.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectMapper projectMapper;

    public ResponseDTO getFinishDate(){
        var project = projectRepository.findAll().get(0);
        var finishDate = project.getFinishDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        var formatedDate = dateFormat.format(finishDate);

        return new ResponseDTO("Fecha de finalizacion del proyecto: "+formatedDate);
    }
}
