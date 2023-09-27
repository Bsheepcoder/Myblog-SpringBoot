package com.backend.mapper;


import com.backend.entity.Tag;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface TagMapper {
    @Insert("insert into tag(tname) values(#{tag})")
    public boolean addTag(String tag);
    @Select("select * from tag")
    public List<Tag> getTagList();
    @Delete("delete from tag where tid = #{tid}")
    public boolean deleteTag(int tid);

    @Update("update tag set tname = #{tname} where tid = #{tid}")
    public boolean updateTag(int tid,String tname);

    @Select("select tname from tag where tid = #{tid}")
    public String  getTagName(int tid);
}
