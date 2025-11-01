package org.mikufans.repository;

import org.mikufans.entity.Animation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimationRepository extends MongoRepository<Animation, String> {
  List<Animation> findAnimationByAnimationStudio(String animationStudio);

  List<Animation> findAnimationByDirector(String director);

  List<Animation> findAnimationByBroadcastPlatform(String broadcastPlatform);

  List<Animation> findAnimationByTitleContaining(String name);

  List<Animation> findAnimationByOriginalTitle(String name);

  List<Animation> findAnimationByEnglishTitle(String name);

  List<Animation> findAnimationByOriginalTitleContaining(String name);

  List<Animation> findAnimationByEnglishTitleContaining(String name);
}
