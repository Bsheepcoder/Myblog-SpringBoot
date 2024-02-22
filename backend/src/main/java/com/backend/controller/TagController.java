package com.backend.controller;


import com.backend.common.core.entity.StringConstant;
import com.backend.common.core.response.BlogResponse;
import com.backend.entity.Base.BaseTagEntity;
import com.backend.entity.TagEntity;
import com.backend.param.tag.SaveParam;
import com.backend.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/tag/")
@Api(value = "标签接口",tags = {"标签接口"})
public class TagController {
    @Resource
    TagService tagService;

    @GetMapping("/list")
    @ApiOperation(value = "查询",notes = "查询文章列表")
    public BlogResponse getTagList(){
        try{
            return BlogResponse.success(tagService.list());
        }catch (Exception e){
            log.error(e.getMessage());
            return BlogResponse.error();
        }
    }


    @PostMapping("/save")
    @ApiOperation(value = "批量新增或更新",notes = "批量新增或更新标签")
    public BlogResponse addTag(@Valid List<SaveParam> params){
        try{
            return BlogResponse.success(tagService.saveOrUpdateBatch(params.stream().map(e -> e.convert(new TagEntity())).collect(Collectors.toList())));
        }catch (Exception e){
            log.error(e.getMessage());
            return BlogResponse.error();
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "批量删除",notes = "批量删除标签")
    public BlogResponse deleteTag(@RequestParam("ids") String ids){
        try{
            return BlogResponse.success(tagService.removeBatchByIds(Arrays.asList(ids.split(StringConstant.COMMA))));
        }catch (Exception e){
            log.error(e.getMessage());
            return BlogResponse.error();
        }
    }


    @GetMapping("/get")
    @ApiOperation(value = "获取标签信息",notes = "获取标签信息")
    public BlogResponse getTagName(@RequestParam("tid") int tid){
        try{
            return BlogResponse.success(tagService.getById(tid));
        }catch (Exception e){
            log.error(e.getMessage());
            //204 无内容。服务器成功处理，但未返回内容。在未更新网页的情况下，可确保浏览器继续显示当前文档
            return BlogResponse.error(204,"没找到对应的标签");
        }
    }

}
