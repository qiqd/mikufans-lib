package org.mikufans.service.impl;

import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Comic;
import org.mikufans.entity.base.MyPage;
import org.mikufans.repository.ComicRepository;
import org.mikufans.service.ComicService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Comic服务层实现类
 * 实现漫画实体的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class ComicServiceImpl implements ComicService {

  private final ComicRepository comicRepository;

  @Override
  public MyPage<Comic> getAllComics(Integer page, Integer size) {
    return null;
  }

  @Override
  public Comic getComicById(String id) {
    return null;
  }

  @Override
  public void saveBatchComic(List<Comic> comics) {

  }

  @Override
  public void updateComic(Comic comic) {

  }

  @Override
  public boolean deleteComic(String id) {
    return false;
  }

  @Override
  public List<Comic> getComicByTitle(String name) {
    return List.of();
  }
}
