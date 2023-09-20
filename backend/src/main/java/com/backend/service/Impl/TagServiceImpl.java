package com.backend.service.Impl;

import com.backend.entity.ArticleTag;
import com.backend.mapper.TagMapper;
import com.backend.service.TagService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Resource
    TagMapper tagMapper;

    @Override
    public boolean addTag(String tag) {
        return tagMapper.addTag(tag);
    }

    @Override
    public boolean deleteTag(int id) {
        return tagMapper.deleteTag(id);
    }

    @Override
    public boolean updateTag(int id,String tagname) {
        return tagMapper.updateTag(id,tagname);
    }

    @Override
    public List<ArticleTag> getTagList() {
        return tagMapper.getTagList();
    }
}
