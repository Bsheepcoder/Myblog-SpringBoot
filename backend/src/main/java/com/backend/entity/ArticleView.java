package com.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ArticleView {
    int aid;
    String title;
    int tag;
    Date datetime;
    String overview;
}
