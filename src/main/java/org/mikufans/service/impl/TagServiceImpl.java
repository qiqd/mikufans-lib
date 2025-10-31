package org.mikufans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.MyPage;
import org.mikufans.entity.Tag;
import org.mikufans.mapper.TagMapper;
import org.mikufans.service.TagService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Tag服务层实现类
 * 实现标签相关的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

  private final TagMapper tagMapper;

  @Override
  public MyPage<Tag> getAllTags(Integer page, Integer size) {
    Page<Tag> tagPage = new Page<>(page, size);
    Page<Tag> pageResult = query().page(tagPage);
    return new MyPage<>(page, size.longValue(), pageResult.getPages(), pageResult.getTotal(), pageResult.getRecords());
  }

  @Override
  public Tag getTagById(Long id) {
    return tagMapper.selectById(id);
  }

  @Override
  public boolean saveTag(Tag tag) {
    // 设置创建时间
    tag.setId(null);
    tag.setCreatedAt(LocalDateTime.now());
    return save(tag);
  }

  @Override
  public boolean updateTag(Tag tag) {
    return updateById(tag);
  }

  @Override
  public boolean deleteTag(Long id) {
    return removeById(id);
  }

  @Override
  public Tag getTagByName(String name) {
    QueryWrapper<Tag> wrapper = new QueryWrapper<>();
    wrapper.eq("name", name);
    return tagMapper.selectOne(wrapper);
  }
}
