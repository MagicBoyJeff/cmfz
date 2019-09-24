package com.hujie.cmfz;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.hujie.entity.Student;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEasyPoi {


    @Test
    public void testExportEasyPoi() {

        List<Student> lists = new ArrayList<>();
        Student s1 = new Student( "1", "胡杰", new Date() );
        Student s2 = new Student( "2", "李培东", new Date() );
        Student s3 = new Student( "3", "曹学东", new Date() );

        lists.add( s1 );
        lists.add( s2 );
        lists.add( s3 );
        try {
            Workbook workbook = ExcelExportUtil.exportExcel( new ExportParams( "计算机一班学生", "学生" ),
                    Student.class, lists );
            workbook.write( new FileOutputStream( new File("D://easyPoi.xls"   )) );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testExportEasyPois() {

        List<Student> lists = new ArrayList<>();
        Student s1 = new Student( "1", "胡杰", new Date() );
        Student s2 = new Student( "2", "李培东", new Date() );
        Student s3 = new Student( "3", "曹学东", new Date() );

        lists.add( s1 );
        lists.add( s2 );
        lists.add( s3 );
        try {
            //参数：(一级标题，二级标题，表名)，实体类类对象，导出的集合
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生","计算机","学生"),Student.class, lists);

            workbook.write(new FileOutputStream(new File("D://easypois.xls")));

            workbook.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
