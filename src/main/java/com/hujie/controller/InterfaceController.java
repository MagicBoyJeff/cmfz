package com.hujie.controller;


import com.hujie.entity.Album;
import com.hujie.entity.Article;
import com.hujie.entity.Banner;
import com.hujie.service.AlbumService;
import com.hujie.service.ArticleService;
import com.hujie.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class InterfaceController {
    
    @Autowired
    BannerService bannerService;
    @Autowired
    AlbumService albumService;
    @Autowired
    ArticleService articleService;
    

    @RequestMapping("first_page")
    public HashMap<String,Object> first_page(String uid, String type, String sub_type){

        HashMap<String, Object> map = new HashMap<>();
        
        if(uid!= null){
            if(type.equals( "all" )){
                //轮播图数据
                List<Banner> banners = bannerService.showAll();
                map.put( "banner", banners);
                //专辑数据
                List<Album> albums = albumService.showAllAlbum();
                map.put( "album",albums );
                //文章数据
                List<Article> articles = articleService.showAll();
                map.put( "article",articles );

            }
            if(type.equals( "wen" )){

            }
            if(type.equals( "si" )){

                //文章的数据
                if(sub_type!= null){
                    if(sub_type.equals( "ssyj" )){
                        //展示上师言教的文章
                    }else{
                        //展示显密法要的文章
                    }
                }
            }
        }
        return map;
    }
}
