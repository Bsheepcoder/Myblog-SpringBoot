package com.backend.service.Impl;

import com.backend.entity.TagEntity;
import com.backend.mapper.TagMapper;
import com.backend.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TagServiceImpl extends ServiceImpl<TagMapper,TagEntity> implements TagService {
}
