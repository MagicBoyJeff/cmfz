package com.hujie.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {

    @Excel( name = "编号")
    private String id;
    @Excel( name = "标题")
    private String title;
    @Excel( name = "内容")
    private String content;
    @Excel( name = "发表时间",format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    private Date create_date;
    @Excel( name = "作者")
    private String author;
}
