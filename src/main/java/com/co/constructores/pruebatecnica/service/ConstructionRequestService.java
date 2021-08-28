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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


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

    public ResponseDTO createConstructionRequest(ConstructionRequestDTO constructionRequestDTO) throws ParseException {
        var constructionRequestEntity = constructionRequestMapper.constructionRequestDTOToEntity(constructionRequestDTO);
        var project = projectRepository.findAll().get(0);
        var constructionType = constructionRequestEntity.getConstructionType();
        var constructionEntity = constructionRepository.findByConstructionType(constructionType);
        var daysToConstruct = constructionEntity.getDaysToBuild();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        constructionRequestEntity.setInitialDate(dateFormat.format(setDates.setInitialDate(project.getFinishDate())));
        constructionRequestEntity.setFinalDate(dateFormat.format(setDates.setFinalDate(dateFormat.parse(constructionRequestEntity.getInitialDate()),daysToConstruct)));
        constructionRequestEntity.setState("pending");


        if(validateIfCoordinatesAreOcuppied(constructionRequestEntity)){
            return new ResponseDTO("No se puede crear su solicitud las coordenadas ya están ocupadas");
        }

        if(!validateIfEnoughMaterials(constructionEntity)){
            return new ResponseDTO("No se puede crear su solicitud, no hay materiales suficientes");
        };

        constructionRequestRepository.save(constructionRequestEntity);
        subtractMaterials(constructionEntity);

        project.setFinishDate(dateFormat.parse(constructionRequestEntity.getFinalDate()));
        projectRepository.save(project);



        return new ResponseDTO("Solicitud creada satisfactoriamente, tu proyecto se comenzará a construir el: "+constructionRequestEntity.getInitialDate());
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
        isEnoughMaterials = actualBrickQuantity >= constructionEntity.getBrickQuantity() && actualWoodQuantity >= constructionEntity.getWoodQuantity() && actualCementQuantity >= constructionEntity.getCementQuantity() && actualGravelQuantity >= constructionEntity.getGravelQuantity() && actualSandQuantity >= constructionEntity.getSandQuantity();
        return isEnoughMaterials;
    }



    private boolean validateIfCoordinatesAreOcuppied(ConstructionRequestEntity constructionRequestEntity) {

        var xCoordinate = constructionRequestEntity.getCoordinateX();
        var yCoordinate = constructionRequestEntity.getCoordinateY();

        var constructionInCoordinates = constructionRequestRepository.findByCoordinateXAndCoordinateY(xCoordinate,yCoordinate);
        boolean isTerrainOcupied;
        isTerrainOcupied = constructionInCoordinates != null;
        return isTerrainOcupied;
    }

    //this service runs everyday at 7am
    @Scheduled(cron="0 0 7 * * *")
    private void verifyStartPendingConstruction(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        var actualDate = dateFormat.format(new Date());

        var startingConstruction = constructionRequestRepository.findByInitialDateStartsWith(actualDate);

        if(startingConstruction != null && startingConstruction.getState().equals("pending")){
            startingConstruction.setState("In progress");
            constructionRequestRepository.save(startingConstruction);
        }

    }

    //this service runs everyday at 7pm
    @Scheduled(cron="0 0 19 * * *")
    private void verifyFinishedConstruction(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        var actualDate = dateFormat.format(new Date());

        var startingConstruction = constructionRequestRepository.findByFinalDateStartsWith(actualDate);

        if(startingConstruction != null && startingConstruction.getState().equals("In progress")){
            startingConstruction.setState("Finished");
            constructionRequestRepository.save(startingConstruction);
        }
    }

    public ResponseDTO createReport(){
        var pendingConstructions = constructionRequestRepository.findByState("pending");
        var inProgressConstructions = constructionRequestRepository.findByState("In progress");
        var finishedConstructions = constructionRequestRepository.findByState("Finished");

        FileWriter flwritter = null;
        try{
            flwritter = new FileWriter(new File(System.getProperty("user.home"), "Desktop\\INFORME.txt"));

            BufferedWriter bfwriter= new BufferedWriter(flwritter);
            bfwriter.write("Construcciones pendientes: \n ----------------------------- \n");
            for(ConstructionRequestEntity constructionRequest : pendingConstructions){
                bfwriter.write("- Construcción: "+constructionRequest.getConstructionType()+", Estado: "+constructionRequest.getState()+", Fecha de inicio de obras: " +
                        constructionRequest.getInitialDate()+", Fecha de finalización de obras: "+constructionRequest.getFinalDate()+"\n");
            }
            bfwriter.write("\nConstrucciones en progreso: \n ----------------------------- \n");
            for(ConstructionRequestEntity constructionRequest : inProgressConstructions){
                bfwriter.write("- Construcción: "+constructionRequest.getConstructionType()+", Estado: "+constructionRequest.getState()+", Fecha de inicio de obras: " +
                        constructionRequest.getInitialDate()+", Fecha de finalización de obras: "+constructionRequest.getFinalDate()+"\n");
            }
            bfwriter.write("\nConstrucciones finalizadas: \n ----------------------------- \n");
            for(ConstructionRequestEntity constructionRequest : finishedConstructions){
                bfwriter.write("- Construcción: "+constructionRequest.getConstructionType()+", Estado: "+constructionRequest.getState()+", Fecha de inicio de obras: " +
                        constructionRequest.getInitialDate()+", Fecha de finalización de obras: "+constructionRequest.getFinalDate()+"\n");
            }
            bfwriter.close();

        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if (flwritter != null){
                try{
                    flwritter.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }

        return new ResponseDTO("Informe generado con exito");
    }

    public ResponseDTO showReport(){
        var pendingConstructions = constructionRequestRepository.findByState("pending");
        var inProgressConstructions = constructionRequestRepository.findByState("In progress");
        var finishedConstructions = constructionRequestRepository.findByState("Finished");

        var report = "";
        report=report+("Construcciones pendientes: \n ----------------------------- \n");
            for(ConstructionRequestEntity constructionRequest : pendingConstructions){
                report=report+("- Construcción: "+constructionRequest.getConstructionType()+", Estado: "+constructionRequest.getState()+", Fecha de inicio de obras: " +
                        constructionRequest.getInitialDate()+", Fecha de finalización de obras: "+constructionRequest.getFinalDate()+"\n");
            }
        report=report+("\nConstrucciones en progreso: \n ----------------------------- \n");
            for(ConstructionRequestEntity constructionRequest : inProgressConstructions){
                report=report+("- Construcción: "+constructionRequest.getConstructionType()+", Estado: "+constructionRequest.getState()+", Fecha de inicio de obras: " +
                        constructionRequest.getInitialDate()+", Fecha de finalización de obras: "+constructionRequest.getFinalDate()+"\n");
            }
        report=report+("\nConstrucciones finalizadas: \n ----------------------------- \n");
            for(ConstructionRequestEntity constructionRequest : finishedConstructions){
                report=report+("- Construcción: "+constructionRequest.getConstructionType()+", Estado: "+constructionRequest.getState()+", Fecha de inicio de obras: " +
                        constructionRequest.getInitialDate()+", Fecha de finalización de obras: "+constructionRequest.getFinalDate()+"\n");
            }



        return new ResponseDTO(report);
    }


}
