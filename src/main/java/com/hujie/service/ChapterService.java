package com.hujie.service;

import com.hujie.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ChapterService {
    Map<String,Object> showChapter(Integer page,Integer rows,String albumId);

    String addChapter(Chapter chapter);

    //音频上传
    void upload(MultipartFile url, HttpSession session, String albumId);

    //音频下载
    void download(String audioName, HttpSession session, HttpServletResponse response);

    //删除
    void delete(String[] id);
}
