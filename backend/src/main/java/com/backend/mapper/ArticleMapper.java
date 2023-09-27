package com.backend.mapper;


import com.backend.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface ArticleMapper {

    @Select( "select * from article")
    public List<Article> getArticleList();

    @Insert("insert into article(title,createTime,updateTime,overView,content) " +
            "values (#{title},#{createTime},#{updateTime},#{overview},#{content},,#{visitCount})")
    public boolean addArticle(String title,Date createTime, Date updateTime, String overview, String content,int visitCount);

    @Delete("delete from article where aid = #{aid}")
    public boolean deleteArticle(int aid);

    @Update("update article set title = #{title}  updateTime = #{updateTime} overview = #{overview} content = #{content} where aid = #{aid}")
    public boolean updateArticle(String title, Date updateTime, String overview, String content,int aid);

    @Select("select * from article where aid = #{aid}")
    public Article getArticle(int aid);

}
