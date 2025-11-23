package org.tetofans.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tetofans.entity.Literary;

@Repository
public interface LiteraryRepository extends MongoRepository<Literary, String> {
}
