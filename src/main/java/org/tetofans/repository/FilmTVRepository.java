package org.tetofans.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tetofans.entity.FilmTV;

@Repository
public interface FilmTVRepository extends MongoRepository<FilmTV, String> {
}
