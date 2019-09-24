package com.hujie.controller;

import com.hujie.entity.Chapter;
import com.hujie.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @RequestMapping("showChapter")
    public Map<String,Object> showChap(Integer page,Integer rows, String albumId){
        Map<String,Object> map = chapterService.showChapter( page, rows, albumId );
        return map;
    }


    //章节的增删改操作
    @RequestMapping("edit")
    public Map<String,Object> editChapter(String oper, Chapter chapter,String[] id){
        HashMap<String, Object> map = new HashMap<>();
        if("add".equals( oper )){
            String s = chapterService.addChapter( chapter );
            map.put( "chapterId",s );
        }
        if("del".equals( oper )){
            chapterService.delete( id );
        }
        return map;
    }

    //文件上传
    @RequestMapping("upload")
    public void upload(MultipartFile url, HttpSession session,String chapterId){
        chapterService.upload( url, session, chapterId );
    }

    //文件下载
    @RequestMapping("download")
    public void download(String audioName, HttpSession session, HttpServletResponse response){
        chapterService.download( audioName, session, response );
    }

}
