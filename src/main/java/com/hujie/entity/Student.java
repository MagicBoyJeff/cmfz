package com.hujie.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {


    @Excel( name = "编号")
    private String id;

    @Excel( name="姓名" )
    private String name;

    @Excel( name = "生日", format = "yyyy-MM-dd")
    private Date bir;

}
