package com.co.constructores.pruebatecnica.repository;

import com.co.constructores.pruebatecnica.entity.ConstructionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConstructionRepository extends MongoRepository<ConstructionEntity, String> {
}
