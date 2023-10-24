package com.backend.controller;


import com.backend.entity.ArticleTag;
import com.backend.entity.RestBean;
import com.backend.entity.Tag;
import com.backend.service.TagService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag/")
public class TagController {
    @Resource
    TagService tagService;

    @PostMapping("/add")
    public RestBean<String> addTag(@RequestParam("tagName") String name){
        System.out.println(name);
        if(tagService.addTag(name)){
            return RestBean.success("添加成功！");
        }
        return RestBean.failure(202,"接口添加失败！");
    }

    @PostMapping("/del")
    public RestBean<String> deleteTag(@RequestParam("tagId") int id){
        if(tagService.deleteTag(id)){
            return RestBean.success("删除成功！");
        }
        return RestBean.failure(202,"接口删除失败！");
    }

    @GetMapping("/up")
    public RestBean<String> updateTag(@RequestParam("tagId") int id,@RequestParam("tagName") String name){
        if(tagService.updateTag(id,name)){
            return RestBean.success("更新成功！");
        }
        //  202 已接受。已经接受请求，但未处理完成
        return RestBean.failure(202,"接口更新失败！");
    }

    @GetMapping("/list")
    public RestBean<List<Tag>> getTagList(){
        List<Tag>  list  = tagService.getTagList();
        if(list.size() != 0){
            return RestBean.success(list);
        }
        return RestBean.failure(204,null);
    }

    @GetMapping("/get")
    public RestBean<String> getTagName(@RequestParam("tid") int tid){
        String tname = tagService.getTagName(tid);
        if(tname != null){
            return RestBean.success(tname);
        }
        //204 无内容。服务器成功处理，但未返回内容。在未更新网页的情况下，可确保浏览器继续显示当前文档
        return RestBean.failure(204,"没找到对应的标签");
    }

}
