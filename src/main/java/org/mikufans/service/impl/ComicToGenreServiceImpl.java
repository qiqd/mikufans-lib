package org.mikufans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.ComicToGenre;
import org.mikufans.entity.MyPage;
import org.mikufans.mapper.ComicToGenreMapper;
import org.mikufans.service.ComicToGenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ComicToGenre服务层实现类
 * 实现漫画-类型关联关系的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class ComicToGenreServiceImpl extends ServiceImpl<ComicToGenreMapper, ComicToGenre> implements ComicToGenreService {

  private final ComicToGenreMapper comicToGenreMapper;

  @Override
  public MyPage<ComicToGenre> getAllComicToGenres(Integer page, Integer size) {
    Page<ComicToGenre> comicToGenrePage = new Page<>(page, size);
    comicToGenreMapper.selectPage(comicToGenrePage, null);
    return new MyPage<>(page, size.longValue(), comicToGenrePage.getPages(), comicToGenrePage.getTotal(), comicToGenrePage.getRecords());
  }

  @Override
  public List<ComicToGenre> getGenresByComicId(Long comicId) {
    QueryWrapper<ComicToGenre> wrapper = new QueryWrapper<>();
    wrapper.eq("comic_id", comicId);
    return comicToGenreMapper.selectList(wrapper);
  }

  @Override
  public List<ComicToGenre> getComicsByGenreId(Long genreId) {
    QueryWrapper<ComicToGenre> wrapper = new QueryWrapper<>();
    wrapper.eq("genre_id", genreId);
    return comicToGenreMapper.selectList(wrapper);
  }

  @Override
  public boolean addGenreToComic(Long comicId, Long genreId) {
    // 检查是否已存在
    QueryWrapper<ComicToGenre> wrapper = new QueryWrapper<>();
    wrapper.eq("comic_id", comicId).eq("genre_id", genreId);
    if (comicToGenreMapper.selectOne(wrapper) != null) {
      return false;
    }

    ComicToGenre comicToGenre = new ComicToGenre();
    comicToGenre.setComicId(comicId);
    comicToGenre.setGenreId(genreId);
    return save(comicToGenre);
  }

  @Override
  public void addGenresToComic(Long comicId, List<Long> genreIds) {
    List<ComicToGenre> list = genreIds.stream().map(genreId -> {
      ComicToGenre comicToGenre = new ComicToGenre();
      comicToGenre.setComicId(comicId);
      comicToGenre.setGenreId(genreId);
      return comicToGenre;
    }).collect(Collectors.toList());
    comicToGenreMapper.insert(list);
  }

  @Override
  public boolean removeGenreFromComic(Long comicId, Long genreId) {
    QueryWrapper<ComicToGenre> wrapper = new QueryWrapper<>();
    wrapper.eq("comic_id", comicId).eq("genre_id", genreId);
    return remove(wrapper);
  }

  @Override
  public int clearComicGenres(Long comicId) {
    QueryWrapper<ComicToGenre> wrapper = new QueryWrapper<>();
    wrapper.eq("comic_id", comicId);
    return comicToGenreMapper.delete(wrapper);
  }
}
