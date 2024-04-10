package com.backend.controller;

import com.backend.common.entity.StringConstant;
import com.backend.common.response.BlogResponse;
import com.backend.entity.ArticleEntity;
import com.backend.entity.ArticleTagEntity;
import com.backend.param.article.SaveArticleTagParam;
import com.backend.param.article.SaveParam;
import com.backend.service.ArticleService;
import com.backend.service.ArticleTagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "文章接口")
public class ArticleController {

    @Resource
    ArticleService articleService;

    @Resource
    ArticleTagService articleTagService;

    @PostMapping ("/save")
    @Operation(summary = "批量新增或修改文章",description = "批量新增或修改文章")
    public BlogResponse addArticle(@Valid @RequestBody SaveParam param){
        try{
            ArticleEntity  entity = articleService.addAriticle(param.convert(new ArticleEntity()));
            SaveArticleTagParam saveArticleTagParam = new SaveArticleTagParam(null,entity.getArticleid(),param.getTagid());
            articleTagService.save(saveArticleTagParam.convert(new ArticleTagEntity()));
            return BlogResponse.success();
        }catch (Exception e){
            log.error(e.getMessage());
            return BlogResponse.error(500,"文章添加失败！");
        }
    }

    @GetMapping("/list")
    @Operation(summary = "查询",description = "查询文章列表")
    public BlogResponse getArticleList(){
        try{
            return BlogResponse.success(articleService.list());
        }catch (Exception e){
            log.error(e.getMessage());
            return BlogResponse.error(500,"文章查找失败");
        }
    }

    @PostMapping("/taglist")
    @Operation(summary = "根据标签查询",description = "查询文章列表")
    public BlogResponse getArticleTagList(@RequestParam("tagid") String tagid){
        try{
            return BlogResponse.success(articleService.getTagArticle(tagid));
        }catch (Exception e){
            log.error(e.getMessage());
            return BlogResponse.error(500,"文章查找失败");
        }
    }

    @GetMapping("/page")
    @Operation(summary = "查询文章内容",description = "查询文章内容")
    public BlogResponse getArticle(@RequestParam("aid") String aid){
        try{
            return BlogResponse.success(articleService.getById(aid));
        }catch (Exception e){
            log.error(e.getMessage());
            return BlogResponse.error();
        }
    }

    @PostMapping("/delete")
    @Operation(summary = "删除",description = "删除文章")
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
