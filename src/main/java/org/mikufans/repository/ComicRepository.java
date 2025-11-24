package org.mikufans.repository;

import org.mikufans.entity.Comic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends MongoRepository<Comic, String> {
}
