package com.backend.controller;

import com.backend.entity.Article;
import com.backend.entity.RestBean;
import com.backend.service.ArticleService;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/article/")
public class ArticleController {

    @Resource
    ArticleService articleService;

    @PostMapping ("/add")
    public RestBean<String> addArticle(@RequestParam("title") String title,
                                       @RequestParam("tag") int tag,
                                       @RequestParam("overview") String overview,
                                       @RequestParam("text") String text){

        Date time = new java.sql.Date(new java.util.Date().getTime());
        articleService.addArticle(title,tag,time,overview,text,1);
        return RestBean.success("添加成功！");
    }

    @GetMapping("/list")
    public RestBean<List<Article>> getArticleList(){
        return RestBean.success(articleService.getArticleList());
    }
}
