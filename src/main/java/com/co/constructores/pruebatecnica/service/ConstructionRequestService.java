package com.co.constructores.pruebatecnica.service;

import com.co.constructores.pruebatecnica.dto.ConstructionRequestDTO;
import com.co.constructores.pruebatecnica.dto.ResponseDTO;
import com.co.constructores.pruebatecnica.entity.ConstructionEntity;
import com.co.constructores.pruebatecnica.entity.ConstructionRequestEntity;
import com.co.constructores.pruebatecnica.helpers.SetDates;
import com.co.constructores.pruebatecnica.mapper.ConstructionRequestMapper;
import com.co.constructores.pruebatecnica.repository.ConstructionRepository;
import com.co.constructores.pruebatecnica.repository.ConstructionRequestRepository;
import com.co.constructores.pruebatecnica.repository.MaterialRepository;
import com.co.constructores.pruebatecnica.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstructionRequestService {

    @Autowired
    private ConstructionRequestRepository constructionRequestRepository;
    @Autowired
    private ConstructionRequestMapper constructionRequestMapper;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private SetDates setDates;
    @Autowired
    private ConstructionRepository constructionRepository;
    @Autowired
    private MaterialRepository materialRepository;

    public ResponseDTO createConstructionRequest(ConstructionRequestDTO constructionRequestDTO){
        var constructionRequestEntity = constructionRequestMapper.constructionRequestDTOToEntity(constructionRequestDTO);
        var project = projectRepository.findAll().get(0);
        var constructionType = constructionRequestEntity.getConstructionType();
        var constructionEntity = constructionRepository.findByConstructionType(constructionType);
        var daysToConstruct = constructionEntity.getDaysToBuild();

        constructionRequestEntity.setInitialDate(setDates.setInitialDate(project.getFinishDate()));
        constructionRequestEntity.setFinalDate(setDates.setFinalDate(constructionRequestEntity.getInitialDate(),daysToConstruct));
        constructionRequestEntity.setState("pending");

        if(validateIfCoordinatesAreOcuppied(constructionRequestEntity)){
            return new ResponseDTO("No se puede crear su solicitud las coordenadas ya están ocupadas");
        }

        if(!validateIfEnoughMaterials(constructionEntity)){
            return new ResponseDTO("No se puede crear su solicitud, no hay materiales suficientes");
        };

        subtractMaterials(constructionEntity);

        constructionRequestRepository.save(constructionRequestEntity);

        return new ResponseDTO(constructionRequestEntity.getConstructionType());
    }

    private void subtractMaterials(ConstructionEntity constructionEntity) {
        var actualCementQuantity = materialRepository.findByMaterialName("cement");
        var actualGravelQuantity = materialRepository.findByMaterialName("gravel");
        var actualSandQuantity = materialRepository.findByMaterialName("sand");
        var actualWoodQuantity = materialRepository.findByMaterialName("wood");
        var actualBrickQuantity = materialRepository.findByMaterialName("brick");

        actualCementQuantity.setQuantity((actualCementQuantity.getQuantity())-(constructionEntity.getCementQuantity()));
        materialRepository.save(actualCementQuantity);

        actualGravelQuantity.setQuantity((actualGravelQuantity.getQuantity())-(constructionEntity.getGravelQuantity()));
        materialRepository.save(actualGravelQuantity);

        actualSandQuantity.setQuantity((actualSandQuantity.getQuantity())-(constructionEntity.getSandQuantity()));
        materialRepository.save(actualSandQuantity);

        actualWoodQuantity.setQuantity((actualWoodQuantity.getQuantity())-(constructionEntity.getWoodQuantity()));
        materialRepository.save(actualWoodQuantity);

        actualBrickQuantity.setQuantity((actualBrickQuantity.getQuantity())-(constructionEntity.getBrickQuantity()));
        materialRepository.save(actualBrickQuantity);
    }


    private boolean validateIfEnoughMaterials(ConstructionEntity constructionEntity) {

        var actualCementQuantity = materialRepository.findByMaterialName("cement").getQuantity();
        var actualGravelQuantity = materialRepository.findByMaterialName("gravel").getQuantity();
        var actualSandQuantity = materialRepository.findByMaterialName("sand").getQuantity();
        var actualWoodQuantity = materialRepository.findByMaterialName("wood").getQuantity();
        var actualBrickQuantity = materialRepository.findByMaterialName("brick").getQuantity();
        boolean isEnoughMaterials;
        if(actualBrickQuantity>= constructionEntity.getBrickQuantity() && actualWoodQuantity>= constructionEntity.getWoodQuantity() && actualCementQuantity>= constructionEntity.getCementQuantity() && actualGravelQuantity>= constructionEntity.getGravelQuantity() && actualSandQuantity>= constructionEntity.getSandQuantity()){
            isEnoughMaterials = true;
        }else{
            isEnoughMaterials = false;
        }
        return isEnoughMaterials;
    }



    private boolean validateIfCoordinatesAreOcuppied(ConstructionRequestEntity constructionRequestEntity) {

        var xCoordinate = constructionRequestEntity.getCoordinateX();
        var yCoordinate = constructionRequestEntity.getCoordinateY();

        var constructionInCoordinates = constructionRequestRepository.findByCoordinateXAndCoordinateY(xCoordinate,yCoordinate);
        boolean isTerrainOcupied;
        if(constructionInCoordinates!=null){
            isTerrainOcupied = true;
        }else{
            isTerrainOcupied = false;
        }
        return isTerrainOcupied;
    }


}
