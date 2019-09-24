package com.hujie.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.hujie.entity.Article;
import com.hujie.entity.Banner;
import com.hujie.service.ArticleService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @RequestMapping("showArt")
    public Map<String,Object> showAllArt(Integer page,Integer rows){
        Map<String, Object> map = articleService.showAllArticle( page, rows );
        return map;
    }


    
    
    @RequestMapping("addArti")
    public Map<String,Object> addArt(Article article){
        Map<String, Object> map = articleService.addArt( article );
        return map;
    }

    @RequestMapping("updateAtri")
    public Map<String,Object> updateArt(Article article){
        Map<String, Object> map = articleService.updateArt( article );
        return map;
    }

    @RequestMapping("delArit")
    public void deleteArit(String[] id,String oper){
        if(oper.equals( "del" )){
            articleService.delArt( id );
        }
    }


    //导出所有文章到Excel
    @RequestMapping("easypoiArticle")
    public void easypoiArticle(){
        List<Article> articles = articleService.showAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("文章信息","文章"),
                Article.class, articles);

        try {
            workbook.write( new FileOutputStream( "D://文章_table.xls" ) );
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
