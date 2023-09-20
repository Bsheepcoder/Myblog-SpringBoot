package com.backend.mapper;


import com.backend.entity.ArticleTag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper {
    @Insert("insert into articleTag(tname) values(#{tag})")
    public boolean addTag(String tag);
    @Select("select * from articleTag")
    public List<ArticleTag> getTagList();
    @Delete("delete from articleTag where tid = #{tid}")
    public boolean deleteTag(int tid);

    @Update("update articleTag set tname = #{tname} where tid = #{tid}")
    public boolean updateTag(int tid,String tname);
}
