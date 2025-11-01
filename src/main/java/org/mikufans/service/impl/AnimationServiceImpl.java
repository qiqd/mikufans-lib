package org.mikufans.service.impl;

import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Animation;
import org.mikufans.entity.MyPage;
import org.mikufans.repository.AnimationRepository;
import org.mikufans.service.AnimationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimationServiceImpl implements AnimationService {
  private final AnimationRepository animationRepository;

  @Override
  public MyPage<Animation> getAllAnimations(Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page - 1, size);
    org.springframework.data.domain.Page<Animation> animationPage = animationRepository.findAll(pageable);
    List<Animation> records = animationPage.getContent();
    return new MyPage<>(page, size.longValue(), (long) animationPage.getTotalPages(), animationPage.getTotalElements(), records);
  }

  @Override
  public Animation getAnimationById(String id) {
    return animationRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void saveBatchAnimations(List<Animation> animations) {
    animations.forEach(animation -> animation.setId(null));
    animationRepository.saveAll(animations);
  }

  @Override
  public boolean updateAnimation(Animation animation) {
    animationRepository.save(animation);
    return true;
  }

  @Override
  public boolean deleteAnimation(String id) {
    animationRepository.deleteById(id);
    return true;
  }

  @Override
  public List<Animation> getAnimationsByStudio(String studio) {
    return animationRepository.findAnimationByAnimationStudio(studio);
  }

  @Override
  public List<Animation> getAnimationsByDirector(String director) {
    return animationRepository.findAnimationByDirector(director);
  }

  @Override
  public List<Animation> getAnimationsByBroadcastPlatform(String platform) {
    return animationRepository.findAnimationByBroadcastPlatform(platform);
  }

  @Override
  public List<Animation> getAnimationsByTitle(String name) {
    return animationRepository.findAnimationByTitleContaining(name);
  }
}
