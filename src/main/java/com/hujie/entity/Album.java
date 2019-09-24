package com.hujie.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;



@Data
@NoArgsConstructor
@AllArgsConstructor
//专辑
public class Album {

    @Excel( name = "编号")
    private String id;
    @Excel( name = "标题")
    private String title;
    //评分

    private Integer score;
    @Excel( name = "作者")
    private String author;
    //章节的数量
    @Excel( name = "数量")
    private Integer count;
    //简介
    @Excel( name = "简介")
    private String brief;
    //播音
    @Excel( name = "播音")
    private String broadcast;
    @Excel( name = "上线时间" ,format="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_date;
    @Excel( name = "封面",type=2)
    private String cover_pic;
}
