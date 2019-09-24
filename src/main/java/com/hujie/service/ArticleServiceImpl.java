package com.hujie.service;

import com.hujie.entity.Article;
import com.hujie.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
@Transactional

public class ArticleServiceImpl implements ArticleService {


    @Autowired
    ArticleMapper articleMapper;

    @Override
    public Map<String, Object> showAllArticle(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();

        //当前页号
        map.put( "page",page );

        //总条数
        Integer totalCount = articleMapper.totalCount();
        map.put( "records",totalCount );

        //总页数  总条数除以当前内容数量
        Integer pageCount = 0;
        if(totalCount % rows!=0){
            pageCount = totalCount/rows+1;
        }else{
            pageCount = totalCount/rows;
        }
        map.put( "total",pageCount );
        //查寻的数据
        List<Article> articles = articleMapper.showAll( page, rows );
        map.put( "rows",articles );

        return map;
    }

    @Override
    public Map<String,Object> addArt(Article article) {
        HashMap<String, Object> map = new HashMap<>();
        String id = UUID.randomUUID().toString();
        article.setId( id );
        article.setCreate_date( new Date() );
        articleMapper.addArticle( article );
        return map;
    }

    @Override
    public void delArt(String[] id) {
        articleMapper.delArt( id );
    }

    @Override
    public Map<String, Object> updateArt(Article article) {
        HashMap<String, Object> map = new HashMap<>();
        articleMapper.updateArt( article );
        return map;
    }

    @Override
    public List<Article> showAll() {
        return articleMapper.showAlls();
    }
}
