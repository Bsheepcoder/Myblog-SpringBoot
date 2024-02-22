package com.backend.controller;

import com.backend.common.core.entity.StringConstant;
import com.backend.common.core.response.BlogResponse;
import com.backend.entity.ArticleEntity;
import com.backend.param.article.SaveParam;
import com.backend.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/article/")
@AllArgsConstructor
@Api(value = "文章接口",tags = {"文章接口"})
public class ArticleController {

    @Resource
    ArticleService articleService;

    @PostMapping ("/save")
    @ApiOperation(value = "批量新增或更新",notes = "新增文章")
    public BlogResponse addArticle(@Valid List<SaveParam> params){
        try{
            articleService.saveOrUpdateBatch(params.stream().map(e -> e.convert(new ArticleEntity())).collect(Collectors.toList()));
            return BlogResponse.success("添加成功！");
        }catch (Exception e){
            log.error(e.getMessage());
            return BlogResponse.error(500,"接口添加失败！");
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询",notes = "查询文章列表")
    public BlogResponse getArticleList(){
        try{
            return BlogResponse.success(articleService.list());
        }catch (Exception e){
            log.error(e.getMessage());
            return BlogResponse.error(500,"文章查找失败");
        }
    }


    @GetMapping("/page")
    @ApiOperation(value = "查询文章内容",notes = "查询文章内容")
    public BlogResponse getArticle(@RequestParam("aid") int aid){
        try{
            return BlogResponse.success(articleService.getById(aid));
        }catch (Exception e){
            log.error(e.getMessage());
            return BlogResponse.error();
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除",notes = "删除文章")
    public BlogResponse deleteArticle(@RequestParam("aids") String aids){
        try{
            articleService.removeBatchByIds(Arrays.asList(aids.split(StringConstant.COMMA)));
            return BlogResponse.success();
        }catch (Exception e){
            log.error(e.getMessage());
            return BlogResponse.error();
        }
    }
}
