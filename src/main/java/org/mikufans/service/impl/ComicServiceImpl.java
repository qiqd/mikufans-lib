package org.mikufans.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Comic;
import org.mikufans.entity.MyPage;
import org.mikufans.mapper.ComicMapper;
import org.mikufans.service.ComicService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Comic服务层实现类
 * 实现漫画实体的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class ComicServiceImpl extends ServiceImpl<ComicMapper, Comic> implements ComicService {

  private final ComicMapper comicMapper;

  @Override
  public MyPage<Comic> getAllComics(Integer page, Integer size) {
    Page<Comic> pageResult = comicMapper.selectPage(new Page<>(page, size), null);
    return new MyPage<>(page, size.longValue(), pageResult.getPages(), pageResult.getTotal(), pageResult.getRecords());
  }

  @Override
  public Comic getComicById(Long id) {
    return comicMapper.selectById(id);
  }

  @Override
  public void saveBatchComic(List<Comic> comics) {
    comics.forEach(comic -> comic.setId(null));
    comicMapper.insert(comics);
  }

  @Override
  public void updateComic(Comic comic) {
    comicMapper.updateById(comic);
  }

  @Override
  public boolean deleteComic(Long id) {
    return comicMapper.deleteById(id) > 0;
  }
}