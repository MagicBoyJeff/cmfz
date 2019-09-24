package com.hujie.mapper;

import com.hujie.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    List<Article> showAll(@Param( "page" )Integer page,@Param( "rows" )Integer rows);

    Integer totalCount();

    void  addArticle(Article article);

    void updateArt(Article article);

    void delArt(String[] id);

    List<Article> showAlls();
}
