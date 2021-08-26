package com.co.constructores.pruebatecnica.repository;

import com.co.constructores.pruebatecnica.entity.ProjectEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<ProjectEntity, String> {
}
