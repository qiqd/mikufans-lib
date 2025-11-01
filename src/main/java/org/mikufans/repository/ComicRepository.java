package org.mikufans.repository;

import org.mikufans.entity.Comic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComicRepository extends MongoRepository<Comic, String> {
  List<Comic> findByTitleContaining(String name);
}
