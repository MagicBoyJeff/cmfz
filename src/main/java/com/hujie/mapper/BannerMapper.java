package com.hujie.mapper;

import com.hujie.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerMapper {
    List<Banner> showAllBanner(@Param("page") Integer page, @Param("rows") Integer rows);

    Integer totalCount();

    void addBanner(Banner banner);

    int updateByPrimaryKeySelective(Banner banner);

    //删除轮播图
    int deleteBanner(String[] id);

    int updateBanner(Banner banner);

    List<Banner> showAll();




}
