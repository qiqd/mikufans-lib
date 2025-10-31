package org.mikufans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.AnimationToGenre;
import org.mikufans.entity.MyPage;
import org.mikufans.mapper.AnimationToGenreMapper;
import org.mikufans.service.AnimationToGenreService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AnimationToGenre服务层实现类
 * 实现动画-类型关联关系的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class AnimationToGenreServiceImpl extends ServiceImpl<AnimationToGenreMapper, AnimationToGenre> implements AnimationToGenreService {

  private final AnimationToGenreMapper animationToGenreMapper;

  @Override
  public MyPage<AnimationToGenre> getAllAnimationToGenres(Integer page, Integer size) {
    Page<AnimationToGenre> animationToGenrePage = new Page<>(page, size);
    Page<AnimationToGenre> pageResult = query().page(animationToGenrePage);
    return new MyPage<>(page, size.longValue(), pageResult.getPages(), pageResult.getTotal(), pageResult.getRecords());
  }

  @Override
  public List<AnimationToGenre> getGenresAnimationId(Long animationId) {
    QueryWrapper<AnimationToGenre> wrapper = new QueryWrapper<>();
    wrapper.eq("animation_id", animationId);
    return animationToGenreMapper.selectList(wrapper);
  }

  @Override
  public List<AnimationToGenre> getAnimationsByGenreId(Long genreId) {
    QueryWrapper<AnimationToGenre> wrapper = new QueryWrapper<>();
    wrapper.eq("genre_id", genreId);
    return animationToGenreMapper.selectList(wrapper);
  }

  @Override
  public void saveBatchAnimationToGenres(List<AnimationToGenre> animationToGenres) {
    animationToGenres.forEach(animationToGenre -> animationToGenre.setId(null));
    animationToGenreMapper.insert(animationToGenres);
  }

  @Override
  public boolean deleteByAnimationId(Long animationId) {
    QueryWrapper<AnimationToGenre> wrapper = new QueryWrapper<>();
    wrapper.eq("animation_id", animationId);
    return animationToGenreMapper.delete(wrapper) > 0;
  }

  @Override
  public boolean deleteByGenreId(Long genreId) {
    QueryWrapper<AnimationToGenre> wrapper = new QueryWrapper<>();
    wrapper.eq("genre_id", genreId);
    return animationToGenreMapper.delete(wrapper) > 0;
  }
}
