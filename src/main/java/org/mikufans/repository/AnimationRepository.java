package org.mikufans.repository;

import org.mikufans.entity.Animation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnimationRepository extends MongoRepository<Animation, String> {
  List<Animation> findAnimationByAnimationStudio(String animationStudio);

  List<Animation> findAnimationByDirectors(List<String> directors);

  List<Animation> findAnimationByBroadcastPlatform(String broadcastPlatform);

  List<Animation> findAnimationByTitleContaining(String name);

  List<Animation> findAnimationByOriginalTitle(String name);

  List<Animation> findAnimationByEnglishTitle(String name);

  List<Animation> findAnimationByOriginalTitleContaining(String name);

  List<Animation> findAnimationByEnglishTitleContaining(String name);

  List<Animation> findAnimationsByReleaseDateBetween(LocalDate releaseDateAfter, LocalDate releaseDateBefore);

  Page<Animation> findByReleaseDateBetween(LocalDate releaseDateAfter, LocalDate releaseDateBefore, Pageable pageable);
}
