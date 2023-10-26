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

    @Select("select * from article_view where aId in (select article_tag.aId from article_tag where tId = #{tid}) order by createDate DESC")
    public List<ArticleView> getArticleListByTid(int tid);

    @Insert("insert into article(aId,aTitle,createDate,updateDate,overView,content,visitCount) " +
            "values (#{aid},#{title},#{createTime},#{updateTime},#{overview},#{content},#{visitCount})")
    public boolean addArticle(int aid,String title,Date createTime, Date updateTime, String overview, String content,int visitCount);

    @Delete("delete from article where aId = #{aid}")
    public boolean deleteArticle(int aid);

    @Update("update article set aTitle = #{title} , updateDate = #{updateTime} ,overview = #{overview} ,content = #{content} where aId = #{aid}")
    public boolean updateArticle(String title, Date updateTime, String overview, String content,int aid);

    @Select("select * from article where aid = #{aid}")
    public Article getArticle(int aid);

    @Select("select count(*) from article")
    public int getArticleNum();

}
