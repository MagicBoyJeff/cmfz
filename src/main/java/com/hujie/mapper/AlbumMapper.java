package com.hujie.mapper;

import com.hujie.entity.Album;

import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface AlbumMapper {
    List<Album> showAlbum(@Param("page") Integer page, @Param("rows") Integer rows);

    Integer totalCount();

    void addAlbum(Album album);

    int updateByPrimaryKeySelective(Album album);

    void updateAlbum(Album album);

    void deleteAlbum(String[] id);

    List<Album> showAll();
}
