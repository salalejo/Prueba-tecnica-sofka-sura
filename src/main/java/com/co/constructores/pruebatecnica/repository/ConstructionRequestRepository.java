package com.co.constructores.pruebatecnica.repository;

import com.co.constructores.pruebatecnica.entity.ConstructionRequestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

public interface ConstructionRequestRepository extends MongoRepository<ConstructionRequestEntity, String> {
    ConstructionRequestEntity findByCoordinateXAndCoordinateY(int xCoordinate, int yCoordinate);
    ConstructionRequestEntity findByInitialDateStartsWith(String date);
    ConstructionRequestEntity findByFinalDateStartsWith(String date);
}
