package com.hujie.service;


import com.hujie.entity.Album;
import com.hujie.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {


    @Autowired
    AlbumMapper albumMapper;

    @Override
    public Map<String,Object> showAlbum(Integer page, Integer rows) {

        HashMap<String, Object> map = new HashMap<>();
        //当前页号
        map.put( "page",page );
        //总条数
        Integer totalCount = albumMapper.totalCount();
        map.put( "records",totalCount );
        //总页数
        Integer pageCount = 0;
        if(totalCount%rows!=0){
            pageCount=totalCount/rows+1;
        }else{
            pageCount=totalCount/rows;
        }
        map.put( "total",pageCount );
        //当前数据内容
        List<Album> albums = albumMapper.showAlbum( page, rows );
        map.put( "rows",albums );
        return map;
    }

    @Override
    public String addAlbum(Album album) {
        String id = UUID.randomUUID().toString();
        album.setId( id );
        album.setCreate_date( new Date() );
        album.setCount( 2 );
        albumMapper.addAlbum( album );
        return album.getId();
    }


    @Override
    public void upload(MultipartFile cover_pic, HttpSession session, String albumId) {
        //判断文件夹是否存在
        String realPath = session.getServletContext().getRealPath( "/upload/img" );
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String filename = cover_pic.getOriginalFilename();
        String str = new Date().getTime()+"_"+filename;
        try {
            cover_pic.transferTo( new File( realPath,str ) );
            Album album = new Album();
            album.setId( albumId );
            album.setCover_pic( str );
            albumMapper.updateByPrimaryKeySelective( album );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAlbum(Album album) {
        if(album.getCover_pic().equals( "" )){
            album.setCover_pic( null );
        }
        albumMapper.updateAlbum( album );
    }

    @Override
    public void deleteAlbum(String[] id) {
        albumMapper.deleteAlbum( id );
    }

    @Override
    public List<Album> showAllAlbum() {
        return albumMapper.showAll();
    }
}
