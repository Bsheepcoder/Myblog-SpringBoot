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
    public RestBean<String> addTag(@RequestParam("tagName") String name){
        if(tagService.addTag(name)){
            return RestBean.success("添加成功！");
        }
        return RestBean.failure(500,"接口添加失败！");
    }

    @GetMapping("/del")
    public RestBean<String> deleteTag(@RequestParam("tagId") int id){
        if(tagService.deleteTag(id)){
            return RestBean.success("删除成功！");
        }
        return RestBean.failure(500,"接口删除失败！");
    }

    @GetMapping("/up")
    public RestBean<String> updateTag(@RequestParam("tagId") int id,@RequestParam("tagName") String name){
        if(tagService.updateTag(id,name)){
            return RestBean.success("更新成功！");
        }
        return RestBean.failure(500,"接口更新失败！");
    }

    @GetMapping("/list")
    public RestBean<List<ArticleTag>> getTagList(){
        List<ArticleTag>  list  = tagService.getTagList();
        if(list.size() != 0){
            return RestBean.success(list);
        }
        return RestBean.failure(500,null);
    }

}
