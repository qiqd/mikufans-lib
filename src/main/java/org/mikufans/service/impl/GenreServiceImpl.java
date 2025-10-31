package org.mikufans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Genre;
import org.mikufans.entity.MyPage;
import org.mikufans.mapper.GenreMapper;
import org.mikufans.service.GenreService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Genre服务层实现类
 * 实现类型相关的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class GenreServiceImpl extends ServiceImpl<GenreMapper, Genre> implements GenreService {


  private final GenreMapper genreMapper;

  @Override
  public MyPage<Genre> getAllGenres(Integer page, Integer size) {
    Page<Genre> genrePage = new Page<>(page, size);
    Page<Genre> pageResult = query().page(genrePage);
    return new MyPage<>(page, size.longValue(), pageResult.getPages(), pageResult.getTotal(), pageResult.getRecords());
  }

  @Override
  public Genre getGenreById(Long id) {
    return genreMapper.selectById(id);
  }

  @Override
  public boolean saveGenre(Genre genre) {
    // 设置创建时间
    genre.setCreatedAt(LocalDate.now());
    return save(genre);
  }

  @Override
  public boolean updateGenre(Genre genre) {
    return updateById(genre);
  }

  @Override
  public boolean deleteGenre(Long id) {
    return removeById(id);
  }

  @Override
  public Genre getGenreByName(String name) {
    QueryWrapper<Genre> wrapper = new QueryWrapper<>();
    wrapper.eq("name", name);
    return genreMapper.selectOne(wrapper);
  }
}
