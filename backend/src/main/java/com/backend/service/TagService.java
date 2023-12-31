package com.backend.service;

import com.backend.entity.ArticleTag;
import com.backend.entity.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    public boolean addTag(String tag);

    public List<Tag> getTagList();

    public boolean deleteTag(int id);

    public boolean updateTag(int id,String tagname);

    public String getTagName(int tid);

}
