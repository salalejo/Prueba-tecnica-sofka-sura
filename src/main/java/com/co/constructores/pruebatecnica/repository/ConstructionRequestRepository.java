package com.co.constructores.pruebatecnica.repository;

import com.co.constructores.pruebatecnica.entity.ConstructionRequestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConstructionRequestRepository extends MongoRepository<ConstructionRequestEntity, String> {
}
