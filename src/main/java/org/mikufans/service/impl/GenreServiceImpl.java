package org.mikufans.service.impl;

import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Genre;
import org.mikufans.entity.MyPage;
import org.mikufans.repository.GenreRepository;
import org.mikufans.service.GenreService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Genre服务层实现类
 * 实现类型相关的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

  private final GenreRepository genreRepository;

  @Override
  public MyPage<Genre> getAllGenres(Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page - 1, size);
    org.springframework.data.domain.Page<Genre> pageResult = genreRepository.findAll(pageable);
    List<Genre> records = pageResult.getContent();
    return new MyPage<>(page, size.longValue(), (long) pageResult.getTotalPages(), pageResult.getTotalElements(), records);
  }

  @Override
  public Genre getGenreById(String id) {
    return genreRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean saveGenre(Genre genre) {
    // 设置创建时间
    genre.setId(null);
    genre.setCreatedAt(LocalDate.now());
    genreRepository.save(genre);
    return true;
  }

  @Override
  public boolean updateGenre(Genre genre) {
    genreRepository.save(genre);
    return true;
  }

  @Override
  public boolean deleteGenre(String id) {
    genreRepository.deleteById(id);
    return true;
  }

  @Override
  public List<Genre> getGenreByName(String name) {
    return genreRepository.findAllByNameContaining(name);
  }
}
