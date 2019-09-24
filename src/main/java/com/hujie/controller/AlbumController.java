package com.hujie.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.hujie.entity.Album;
import com.hujie.entity.Banner;
import com.hujie.service.AlbumService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumController {


    @Autowired
    AlbumService albumService;

    @RequestMapping("showAlbum")
    public Map<String,Object> showAlbum(Integer page,Integer rows){
        Map<String, Object> map = albumService.showAlbum( page, rows );
        return map;
    }

    @RequestMapping("edit")
    public Map<String,Object> editAlbum(String oper, Album album,String [] id){
        Map<String, Object> map = new HashMap<>();
        if("add".equals( oper )){
            String s = albumService.addAlbum( album );
            map.put( "albumId",s );
        }else if("edit".equals( oper )){
            if(album.getCover_pic()==""){
                albumService.updateAlbum( album );
                map.put( "id","" );
            }else {
                albumService.updateAlbum( album );
                map.put( "id", album.getId());
            }
        }else if("del".equals( oper )){
            albumService.deleteAlbum( id );
        }
        return map;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile cover_pic, HttpSession session,String albumId){
        albumService.upload( cover_pic, session, albumId );
    }


    //导出专辑信息
    @RequestMapping("easyPoiExportAlbum")
    public void easyPoiExportAlbum(HttpSession session){
        //获取图片的相对路径
        String realPath = session.getServletContext().getRealPath( "/upload/img/" );
        List<Album> albums = albumService.showAllAlbum();
        for (Album album : albums) {
            album.setCover_pic( realPath+album.getCover_pic() );
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑信息","专辑"),
                Album.class, albums);

        try {
            workbook.write( new FileOutputStream( "D://专辑信息_table.xls" ) );
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
