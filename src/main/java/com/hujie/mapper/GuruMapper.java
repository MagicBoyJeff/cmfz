package com.hujie.mapper;

import com.hujie.entity.Guru;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuruMapper {

    List<Guru> showAll(@Param( "page" )Integer page,@Param( "rows" )Integer rows);

    Integer totalCount();

    int updateGuru(Guru guru);
}
