package org.mikufans.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Animation;
import org.mikufans.entity.AnimationToGenre;
import org.mikufans.entity.MyPage;
import org.mikufans.entity.Tag;
import org.mikufans.mapper.AnimationMapper;
import org.mikufans.service.AnimationService;
import org.mikufans.service.AnimationToGenreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimateServiceImpl extends ServiceImpl<AnimationMapper, Animation> implements AnimationService {
  private final AnimationMapper animationMapper;
  private final AnimationToGenreService animationToGenreService;

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
  public void saveBatchAnimations(List<Animation> animations, List<Tag> tags) {
    animations.forEach(animation -> animation.setId(null));
    animationMapper.insert(animations);
    ArrayList<AnimationToGenre> animationToGenres = new ArrayList<>();
    for (int i = 0; i < animations.size(); i++) {
      AnimationToGenre animationToGenre = new AnimationToGenre();
      animationToGenre.setAnimationId(animations.get(i).getId());
      animationToGenre.setGenreId(tags.get(i).getId());
      animationToGenres.add(animationToGenre);
    }
    animationToGenreService.saveBatchAnimationToGenres(animationToGenres);
  }

  @Override
  public boolean updateAnimation(Animation animation) {
    return updateById(animation);
  }

  @Override
  public boolean deleteAnimation(Long id) {
    return removeById(id);
  }
}
