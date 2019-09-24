package com.hujie.service;

import com.hujie.entity.Banner;
import com.hujie.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;


@Service
@Transactional
public class BannerServiceImpl implements BannerService {


    @Autowired
    BannerMapper bannerMapper;

    @Override
    public HashMap<String, Object> updateStatus(Banner banner) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            bannerMapper.updateBanner( banner);
            map.put( "success","200" );
            map.put( "msg","修改成功" );
        } catch (Exception e) {
            e.printStackTrace();
            map.put( "fail","400" );
            map.put( "msg","修改失败" );
        }
        return map;
    }

    @Override
    public List<Banner> showAll() {
        return bannerMapper.showAll();
    }


    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)

    @Override
    public Map<String,Object> showAllBanner(Integer page, Integer rows) {
        //接收客户端数据
        Map<String, Object> map = new HashMap<>();
        //当前页号
        map.put( "page",page );
        //总条数
        Integer totalCount = bannerMapper.totalCount();
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
        List<Banner> banners = bannerMapper.showAllBanner( page, rows );
        map.put( "rows",banners );
        return map;
    }


    @Override
    public void deleteByIds(String[] ids) {
         bannerMapper.deleteBanner( ids );
    }

    @Override
    public void updateBanner(Banner banner) {
        if(banner.getImg_pic().equals( "" )){
            banner.setImg_pic( null );
        }
        bannerMapper.updateBanner( banner );
    }

    @Override
    public String addBanner(Banner banner) {
        String id = UUID.randomUUID().toString();
        banner.setId( id );
        banner.setCreate_date( new Date() );
        banner.setStatus( "1" );
        bannerMapper.addBanner( banner );
        return banner.getId();
    }


    @Override
    public void upload(MultipartFile img_pic, HttpSession session, String bannerId) {
        //判断上传的文件夹是否存在
        String realPath = session.getServletContext().getRealPath("/upload/img");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //文件上传
        String originalFilename = img_pic.getOriginalFilename();//文件名字
        String str = new Date().getTime() + "_" + originalFilename;
        try {
            img_pic.transferTo(new File(realPath, str));
            Banner banner = new Banner();
            banner.setId(bannerId);
            banner.setImg_pic(str);
            bannerMapper.updateByPrimaryKeySelective( banner );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
