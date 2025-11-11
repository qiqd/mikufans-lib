package org.mikufans.service.impl;

import lombok.RequiredArgsConstructor;
import org.mikufans.entity.base.MyPage;
import org.mikufans.entity.base.Tag;
import org.mikufans.repository.TagRepository;
import org.mikufans.service.TagService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Tag服务层实现类
 * 实现标签相关的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

  private final TagRepository tagRepository;

  @Override
  public MyPage<Tag> getAllTags(Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page - 1, size);
    org.springframework.data.domain.Page<Tag> pageResult = tagRepository.findAll(pageable);
    List<Tag> records = pageResult.getContent();
    return new MyPage<>(page, size.longValue(), (long) pageResult.getTotalPages(), pageResult.getTotalElements(), records);
  }

  @Override
  public Tag getTagById(String id) {
    return tagRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean saveTag(Tag tag) {
    tag.setId(null);
    tag.setCreatedAt(LocalDateTime.now());
    tagRepository.save(tag);
    return true;
  }

  @Override
  public boolean updateTag(Tag tag) {
    tagRepository.save(tag);
    return true;
  }

  @Override
  public boolean deleteTag(String id) {
    tagRepository.deleteById(id);
    return true;
  }

  @Override
  public List<Tag> getTagByName(String name) {
    return tagRepository.findAllByName(name);
  }
}
