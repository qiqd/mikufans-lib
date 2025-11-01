package org.mikufans.repository;

import org.mikufans.entity.Character;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends MongoRepository<Character, String> {
  List<Character> findByType(String type);
  

  List<Character> findByNameContaining(String name);
}
