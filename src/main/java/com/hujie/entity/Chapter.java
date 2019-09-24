package com.hujie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//章节
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Chapter implements Serializable {

    private String id;
    private String title;
    private String albumId;
    //大小
    private String size;
    //时长
    private String duration;
    private String url;
}
