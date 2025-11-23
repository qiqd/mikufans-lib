package org.tetofans.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tetofans.entity.Anime;
@Repository
public interface AnimeRepository extends MongoRepository<Anime, String> {
}
