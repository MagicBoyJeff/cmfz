package com.hujie.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Banner {


    @Excel( name = "编号")
    private String id;
    @Excel( name = "标题")
    private String title;
    @Excel( name = "模拟图" ,type=2)
    private String img_pic;
    @Excel( name = "上传时间" ,format="yyyy-MM-dd")
    @JsonFormat (timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_date;
    @Excel( name = "状态")
    private String status;
    @Excel( name = "描述")
    private String description;
}
