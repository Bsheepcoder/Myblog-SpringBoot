package com.backend.service;

import com.backend.entity.Base.BaseTagEntity;
import com.backend.entity.TagEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService extends IService<TagEntity> {

}
