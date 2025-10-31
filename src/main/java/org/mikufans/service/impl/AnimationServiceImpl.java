package org.mikufans.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Animation;
import org.mikufans.entity.MyPage;
import org.mikufans.mapper.AnimationMapper;
import org.mikufans.service.AnimationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimationServiceImpl extends ServiceImpl<AnimationMapper, Animation> implements AnimationService {
  private final AnimationMapper animationMapper;

  @Override
  public MyPage<Animation> getAllAnimations(Integer page, Integer size) {
    Page<Animation> animationPage = new Page<>(page, size);
    List<Animation> records = query().page(animationPage).getRecords();
    return new MyPage<>(page, size.longValue(), animationPage.getPages(), animationPage.getTotal(), records);
  }

  @Override
  public Animation getAnimationById(Long id) {
    return query().eq("id", id).one();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void saveBatchAnimations(List<Animation> animations) {
    animations.forEach(animation -> animation.setId(null));
    animationMapper.insert(animations);
  }

  @Override
  public boolean updateAnimation(Animation animation) {
    return updateById(animation);
  }

  @Override
  public boolean deleteAnimation(Long id) {
    return removeById(id);
  }

  @Override
  public List<Animation> getAnimationsByStudio(String studio) {
    return List.of();
  }

  @Override
  public List<Animation> getAnimationsByDirector(String director) {
    return List.of();
  }

  @Override
  public List<Animation> getAnimationsByBroadcastPlatform(String platform) {
    return List.of();
  }
}
