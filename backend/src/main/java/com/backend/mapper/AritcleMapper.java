package com.backend.mapper;


import com.backend.entity.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface AritcleMapper {

    @Select( " select * from article")
    public List<Article> getArticleList();
    @Insert("insert into article(title,tag,datetime,overview,text) values (#{title},#{tag},#{datetime},#{overview},#{text})")
    public void addArticle(String title, String tag, Date datetime, String overview, String text);
}
