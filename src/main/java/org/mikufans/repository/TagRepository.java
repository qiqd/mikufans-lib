package org.mikufans.repository;

import org.mikufans.entity.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends MongoRepository<Tag, String> {
  Tag findByName(String name);

  List<Tag> findAllByName(String name);
}
