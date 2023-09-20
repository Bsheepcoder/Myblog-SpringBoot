package com.backend.mapper;


import com.backend.entity.Article;
import com.backend.entity.ArticleView;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface ArticleMapper {

    @Select( "select * from article_view")
    public List<ArticleView> getArticleList();

    @Insert("insert into article(title,tag,datetime,overview,text,autherid) values (#{title},#{tag},#{datetime},#{overview},#{text},#{authorid})")
    public boolean addArticle(String title, int tag, Date datetime, String overview, String text,int authorid);

    @Delete("delete from article where  aid = #{aid}")
    public boolean deleteArticle(int aid);

    @Update("update article set title = #{title} tag = #{tag} datetime = #{datetime} overview = #{overview} text = #{text} where aid = #{aid}")
    public boolean updateArticle(int aid ,String title,int tag,Date datetime,String overview,String text);

    @Select("select * from article where aid = #{aid}")
    public Article getArticle(int aid);

}
