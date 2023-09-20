package com.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Article {
    String title;
    int tag;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date datetime;
    String overview;
    String text;
    int aid;
    int autherid;
}
