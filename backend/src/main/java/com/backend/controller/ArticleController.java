package com.backend.controller;

import com.backend.entity.Article;
import com.backend.entity.ArticleView;
import com.backend.entity.RestBean;
import com.backend.service.ArticleService;
import com.backend.service.ArticleTagService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/article/")
public class ArticleController {

    @Resource
    ArticleService articleService;

    @Resource
    ArticleTagService articleTagService;

    @PostMapping ("/add")
    public RestBean<String> addArticle(@RequestParam("title") String title,
                                       @RequestParam("tag") int tid,
                                       @RequestParam("overview") String overview,
                                       @RequestParam("text") String text){

        Date time = new java.sql.Date(new java.util.Date().getTime());
        if(articleService.addArticle(title,time,overview,text,tid)){
            return RestBean.success("添加成功！");
        }
        return RestBean.failure(500,"接口添加失败！");
    }

    @GetMapping("/list")
    public RestBean<List<ArticleView>> getArticleList(){
        return RestBean.success(articleService.getArticleList());
    }

    @GetMapping("/page")
    public RestBean<Article> getArticle(@RequestParam("aid") int aid){
        Article article = articleService.getArticle(aid);
        if(article != null){
            return RestBean.success(article);
        }
        return RestBean.failure(500,null);
    }

    @PostMapping("/delete")
    public RestBean<String> deleteArticle(@RequestParam("aid") int aid){
        if(articleService.deleteArticle(aid)){
            return RestBean.success("已经删除" + aid);
        }
        return RestBean.failure(500,"接口删除文章失败");
    }

    @PostMapping("/update")
    public RestBean<String> updateArticle(@RequestParam("aid") int aid,
                                          @RequestParam("title") String title,
                                          @RequestParam("tag") int tag,
                                          @RequestParam("overview") String overview,
                                          @RequestParam("text") String text
    ){
        Date time = new java.sql.Date(new java.util.Date().getTime());
        boolean flag = articleService.updateArticle(title,time,overview,text,aid);
        if(flag){
            return RestBean.success("已经更新" + aid);
        }
        return RestBean.failure(500,"接口更新文章失败");
    }
}
