package com.backend.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface ArticleTagMapper {

    @Insert("insert into article_tag(aid,tid) value(#{aid},#{tid})")
    public boolean addTagForArticle(int aid,int tid);

    @Delete("delete from article_tag where aid = #{aid}")
    public boolean deleteArticleAllTags(int aid);

    @Delete("delete from article_tag where aid = #{aid}")
    public boolean deleteArticleTag(int aid);

    // 查询文章的所有标签
    @Select("select t.tname from tag t inner join article_tag a on t.tid = a.tId where a.aId = #{aid}")
    public List<String> getArticleTagList(int aid);

    @Insert("update article_tag set tId = #{ntid} where tId = #{tid} and aId = #{aid}")
    public boolean updateArticleTag(int aid,int tid,int ntid);
}
