package com.backend.controller;


import com.backend.entity.ArticleTag;
import com.backend.entity.RestBean;
import com.backend.service.TagService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tag/")
public class TagController {
    @Resource
    TagService tagService;

    @GetMapping("/add")
    public RestBean<String> addTag(@RequestParam("tag") String tag){
        if(tagService.addTag(tag)){
            return RestBean.success("添加成功！");
        }
        return RestBean.failure(500,"接口添加失败！");
    }

    @GetMapping("/del")
    public RestBean<String> deleteTag(@RequestParam("tag") String tag){

    }

    @GetMapping("/up")
    public RestBean<String> updateTag(@RequestParam("tag") String tag){

    }

    @GetMapping("/list")
    public RestBean<List<ArticleTag>> getTagList(@RequestParam("tag") String tag){

    }

}
