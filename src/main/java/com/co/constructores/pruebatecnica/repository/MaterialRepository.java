package com.co.constructores.pruebatecnica.repository;

import com.co.constructores.pruebatecnica.entity.MaterialEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MaterialRepository extends MongoRepository<MaterialEntity, String> {
}
