package com.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ArticleView {

    int aid;
    String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date updateTime;
    String overview;
    int visitCount;

}
