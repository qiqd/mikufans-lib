package org.mikufans.repository;

import org.mikufans.entity.Animation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface AnimationRepository extends MongoRepository<Animation, String> {

  List<Animation> findAnimationByAnimationStudio(String studio);


  List<Animation> findAnimationByBroadcastPlatform(String platform);

  List<Animation> findAnimationByTitleContaining(String name);

  List<Animation> findAnimationByTitleCnContaining(String name);

  List<Animation> findAnimationByTitleEnContaining(String name);

  Page<Animation> findByReleaseDateBetween(LocalDate start, LocalDate end, PageRequest request);

  List<Animation> findBySubIdIn(Collection<String> subIds);
}
