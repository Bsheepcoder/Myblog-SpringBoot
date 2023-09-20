package com.backend.mapper;


import com.backend.entity.Article;
import com.backend.entity.ArticleView;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface AritcleMapper {

    @Select( "select * from article_view")
    public List<ArticleView> getArticleList();

    @Insert("insert into article(title,tag,datetime,overview,text,autherid) values (#{title},#{tag},#{datetime},#{overview},#{text},#{authorid})")
    public boolean addArticle(String title, int tag, Date datetime, String overview, String text,int authorid);
}
