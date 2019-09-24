package com.hujie.mapper;

import com.hujie.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterMapper {

    List<Chapter> showChapter(@Param( "page" )Integer page,@Param( "rows" )Integer rows,@Param( "albumId" ) String albumId);

    Integer totalCount();

    int insert(Chapter chapter);


    int updateByPrimaryKey(Chapter chapter);

    void delete(String[] id);
}
