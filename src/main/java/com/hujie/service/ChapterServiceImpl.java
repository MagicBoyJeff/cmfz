package com.hujie.service;


import com.hujie.entity.Chapter;
import com.hujie.mapper.ChapterMapper;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    ChapterMapper chapterMapper;

    @Override
    public Map<String, Object> showChapter(Integer page, Integer rows, String albumId) {
        HashMap<String, Object> map = new HashMap<>();
       //当前页号
        map.put( "page",page );
        //总条数
        Integer totalCount = chapterMapper.totalCount();
        map.put( "records",totalCount );
        //总页数
        Integer pageCount = 0;
        if(totalCount%rows!=0){
            pageCount=totalCount/rows+1;
        }else{
            pageCount=totalCount/rows;
        }
        map.put( "total",pageCount );
        List<Chapter> chapters = chapterMapper.showChapter( page, rows, albumId );
        map.put( "rows",chapters );
        return map;
    }

    @Override
    public String addChapter(Chapter chapter) {
        String id = UUID.randomUUID().toString();
        chapter.setId( id );
        chapterMapper.insert( chapter );
        return chapter.getId();
    }

    @Override
    public void upload(MultipartFile url, HttpSession session, String chapterId) {
        //1.判断上传的文件夹是否存在
        String realPath = session.getServletContext().getRealPath("/upload/audio");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //2.获得文件真名
        String originalFilename = url.getOriginalFilename();
        //3.为了防止同一个文件上传多次发生覆盖
        String name = new Date().getTime() + "_" + originalFilename;
        try {
            url.transferTo(new File(realPath, name));
            //获得时长
            //1.读取上传完成的音频
            AudioFile read = AudioFileIO.read(new File(realPath, name));
            AudioHeader audioHeader = read.getAudioHeader();
            int trackLength = audioHeader.getTrackLength();
            String seconds = trackLength % 60 + "秒";
            String minute = trackLength / 60 + "分";
            long l = url.getSize();
            String size = l / 1024 / 1024 + "MB";
            Chapter chapter = new Chapter();
            chapter.setId(chapterId);
            chapter.setDuration(minute + seconds);
            chapter.setSize(size);
            chapter.setUrl(name);
            chapterMapper.updateByPrimaryKey(chapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void download(String audioName, HttpSession session, HttpServletResponse response) {
        //1.获得音频的目录
        String realPath = session.getServletContext().getRealPath("/upload/audio");
        //2.获取音频的文件
        File file = new File(realPath, audioName);
        String s = audioName.split("_")[1];
        try {
            String encode = URLEncoder.encode(s, "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //3.设置响应头(以附件的形式下载)
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            FileUtils.copyFile(file, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String[] id) {
        chapterMapper.delete( id );
    }
}
