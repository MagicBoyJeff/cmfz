package com.hujie.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.hujie.entity.City;
import com.hujie.entity.Province;
import com.hujie.entity.User;
import com.hujie.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("showUser")
    public Map<String,Object> showAllUser(Integer page,Integer rows){
        Map<String, Object> map = userService.showAllUser( page, rows );
        return map;
    }

    @RequestMapping("updateStatus")
    public HashMap<String, Object> updateStatus(User user){
        HashMap<String, Object> map = userService.updateStatus( user );
        return map;
    }


    @RequestMapping("easyPoiExport")
    public void easyPoiExport(){


        List<User> list = userService.showAll();


        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户表","用户"),
                User.class, list);

        try {
            workbook.write( new FileOutputStream( "D://cmfz_user.xls" ) );
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    //统计各个月份注册的人数
    @RequestMapping("showUserBySex")
    public Map<String,Object> showUser(){
        Map<String, Object> map = new HashMap<>();
        List<Integer> men = userService.queryBySex( "men" );
        List<Integer> women = userService.queryBySex( "women" );

        map.put( "men" ,men);
        map.put( "women",women );
        map.put( "months", Arrays.asList( "1月", "2月","3月","4月","5月","6月","7月","8月","9月","10月") );
        return map;
    }


    //展示各个省份注册用户的人数  通过地图的形式
    @RequestMapping("queryUserMap")
    public ArrayList<Province> queryUserMap(){
        List<City> men = userService.selectAllCity( "men" );

        List<City> women = userService.selectAllCity( "women" );

        ArrayList<Province> provinces = new ArrayList<>();

        Province pro1 = new Province( "men", men );
        Province pro2 = new Province( "women", women );

        provinces.add( pro1 );
        provinces.add( pro2 );

        return provinces;
    }



    @RequestMapping("edit")
    public void edit(String oper,User user){
        if(oper.equals( "add" )){
            userService.addUser( user );

        }
    }
}
