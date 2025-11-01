package org.mikufans.service.impl;

import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Comic;
import org.mikufans.entity.MyPage;
import org.mikufans.repository.ComicRepository;
import org.mikufans.service.ComicService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    Pageable pageable = PageRequest.of(page - 1, size);
    org.springframework.data.domain.Page<Comic> pageResult = comicRepository.findAll(pageable);
    List<Comic> records = pageResult.getContent();
    return new MyPage<>(page, size.longValue(), (long) pageResult.getTotalPages(), pageResult.getTotalElements(), records);
  }

  @Override
  public Comic getComicById(String id) {
    return comicRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void saveBatchComic(List<Comic> comics) {
    comics.forEach(comic -> comic.setId(null));
    comicRepository.saveAll(comics);
  }

  @Override
  public void updateComic(Comic comic) {
    comicRepository.save(comic);
  }

  @Override
  public boolean deleteComic(String id) {
    comicRepository.deleteById(id);
    return true;
  }

  @Override
  public List<Comic> getComicByTitle(String name) {
    return comicRepository.findByTitleContaining(name);
  }
}
