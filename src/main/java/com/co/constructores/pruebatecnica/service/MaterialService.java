package com.co.constructores.pruebatecnica.service;

import com.co.constructores.pruebatecnica.dto.MaterialDTO;
import com.co.constructores.pruebatecnica.dto.ResponseDTO;
import com.co.constructores.pruebatecnica.mapper.MaterialMapper;
import com.co.constructores.pruebatecnica.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MaterialMapper materialMapper;


    public ResponseDTO addMaterial(MaterialDTO materialDTO){
        var materialEntityToSave = materialMapper.materialDTOToEntity(materialDTO);
        var material = materialRepository.findByMaterialName(materialEntityToSave.getMaterialName());
        boolean materialExists = material != null;
        if(!materialExists){
            return new ResponseDTO("El material que ingresaste no existe");
        }
        var actualMaterialQuantity = material.getQuantity();
        var materialQuantityToAdd = materialEntityToSave.getQuantity();

        if(materialQuantityToAdd<=0){
            return new ResponseDTO("La cantidad de material a ingresar debe ser mayor a cero");
        }

        var newMaterialQuantity = actualMaterialQuantity+materialQuantityToAdd;
        material.setQuantity(newMaterialQuantity);
        materialRepository.save(material);


        return new ResponseDTO("Material añadido con éxito");
    }
}
