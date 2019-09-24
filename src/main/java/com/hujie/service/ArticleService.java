package com.hujie.service;

import com.hujie.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    Map<String,Object> showAllArticle(Integer page, Integer rows);

    Map<String,Object> addArt(Article article);

    void delArt(String[] id);

    Map<String,Object> updateArt(Article article);

    List<Article> showAll();
}
