package com.hujie.service;

import com.hujie.entity.Album;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface AlbumService {
    Map<String, Object> showAlbum(Integer page, Integer rows);

    String addAlbum(Album album);

    void upload(MultipartFile cover_pic, HttpSession session, String albumId);

    void updateAlbum(Album album);

    void deleteAlbum(String[] id);

    List<Album> showAllAlbum();
}


