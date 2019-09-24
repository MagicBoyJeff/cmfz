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
public class User implements Serializable {

    @Excel( name = "编号")
    private String id;
    @Excel( name = "手机号码")
    private String phone_num;
    @Excel( name = "密码")
    private String password;
    //加密
    private String salt;
    @Excel( name = "姓名")
    private String name;
    //法号
    @Excel( name = "法号")
    private String dname;
    @Excel( name = "性别")
    private String sex;
    @Excel( name = "地址")
    private String address;
    @Excel( name = "语录")
    private String sign;
    @Excel( name = "状态")
    private String status;

    @Excel( name = "上线时间" , format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_date;

}
