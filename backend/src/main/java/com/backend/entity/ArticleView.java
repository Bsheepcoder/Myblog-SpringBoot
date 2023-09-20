package com.backend.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleView {
    int aid;
    String title;
    int tag;
    Date datetime;
    String overview;
}
