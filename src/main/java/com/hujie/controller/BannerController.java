package com.hujie.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.hujie.entity.Banner;
import com.hujie.entity.User;
import com.hujie.service.BannerService;
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
@RequestMapping("banner")
public class BannerController {

    @Autowired
    BannerService bannerService;

    @RequestMapping("showAllBanner")
    public Map<String, Object> showAll(Integer page, Integer rows) {
        Map<String, Object> map = bannerService.showAllBanner( page, rows );
        return map;
    }

    @RequestMapping("edit")
    public Map<String, String> edit(String oper, Banner banner, String[] id) {
        Map<String, String> map = new HashMap<>();
        if ("add".equals( oper )) {
            String s = bannerService.addBanner( banner );
            map.put( "msg", "添加成功" );
            map.put( "bannerId", s );
        } else if ("edit".equals( oper )) {
            if (banner.getImg_pic() == "") {
                bannerService.updateBanner( banner );
                map.put( "id", "" );
            } else {
                bannerService.updateBanner( banner );
                map.put( "id", banner.getId() );
                map.put( "msg","修改成功" );
            }
        } else if ("del".equals( oper )) {
            bannerService.deleteByIds( id );
            map.put( "msg", "删除成功" );
        }
        return map;
    }

    //图片上传
    @RequestMapping("upload")
    public void upload(MultipartFile img_pic, HttpSession session, String bannerId) throws Exception {
        bannerService.upload( img_pic, session, bannerId );
    }


    //修改状态
    @RequestMapping("updateStatus")
    public HashMap<String,Object> updateStatus(Banner banner){
        HashMap<String, Object> map = bannerService.updateStatus( banner );
        return map;
    }


    //导出轮播图信息
    @RequestMapping("easyPoiExport")
    public void easyPointExport(HttpSession session){
        String realPath = session.getServletContext().getRealPath( "/upload/img/" );
        List<Banner> banners = bannerService.showAll();
        for (Banner banner : banners) {
            banner.setImg_pic( realPath+banner.getImg_pic() );
        }

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图信息","轮播图"),
                Banner.class, banners);
        try {
            workbook.write( new FileOutputStream( "D://Banner_table.xls" ) );
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
