package com.hujie.service;

import com.hujie.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BannerService {
    public Map<String,Object> showAllBanner(Integer page,Integer rows);

    public String addBanner(Banner banner);

    void upload(MultipartFile img_pic, HttpSession session, String bannerId);

    void deleteByIds(String[] ids);

    void updateBanner(Banner banner);

    HashMap<String,Object> updateStatus(Banner banner);

    List<Banner> showAll();
}
