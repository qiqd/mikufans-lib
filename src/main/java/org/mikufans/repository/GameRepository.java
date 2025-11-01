package org.mikufans.repository;

import org.mikufans.entity.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
  List<Game> findByTitleContaining(String name);

  List<Game> findByOriginalTitleContaining(String name);

  List<Game> findByEnglishTitleContaining(String name);
}
