package com.hujie.cmfz;



import org.apache.poi.hssf.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestPOI {




    //POI导出
    @Test
    public void testExcelOutPutPoi(){
        //创建Excel工作簿对象
        HSSFWorkbook workbook = new HSSFWorkbook();

        //创建工作表
        HSSFSheet sheet = workbook.createSheet( "用户信息" );

        //创建标题行
        HSSFRow row = sheet.createRow( 0 );

        String [] title={"编号","姓名","出生年月"};
        //创建单元格对象
        HSSFCell cell = null;
        for(int i =0;i<title.length;i++){

            //i 标示列索引
            cell = row.createCell( i );
            cell.setCellValue( title[i] );

        }

        //处理日期格式
        HSSFCellStyle cellStyle = workbook.createCellStyle();//样式对象
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat( dataFormat.getFormat( "yyyy年MM月dd日" ) );

        //处理数据行
        row = sheet.createRow( 1 );
        row.createCell( 0 ).setCellValue(1 );
        row.createCell( 1 ).setCellValue( "胡杰" );
        //设置出生年月
        cell = row.createCell( 2 );
        cell.setCellValue( new Date() );
        cell.setCellStyle( cellStyle );

        try {
            workbook.write(new File( "D:\\用户.xls" ) );
            workbook.close();
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    //POI导入
    @Test
    public void testImportExcelPOI(){
        //获取本地文件Excel文件输入流，并常见工作簿对象
        try {
            HSSFWorkbook workbook = new HSSFWorkbook( new FileInputStream( "D:\\用户.xls" ) );

            //获取工作表
            HSSFSheet sheet = workbook.getSheet( "用户信息" );

            //声明行对象
            HSSFRow row = null;

            //注意：获取数据 需要排除标题行 从数据行开始读取
            for(int i =1;i<=sheet.getLastRowNum();i++){

                //获取当前工作表中的数据行信息  数据行索引从1开始
                row = sheet.getRow( i );

                System.out.println((int) row.getCell( 0 ).getNumericCellValue()
                +""+row.getCell( 1 ).getStringCellValue()
                        +""+row.getCell( 2 ).getDateCellValue()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //JxL导出Excel
    //@Test
    /*public void jxlExport() throws WriteException {
        //创建Excel工作簿对象
        try {
            WritableWorkbook workbook = Workbook.createWorkbook( new FileOutputStream( "D:\\用户1.xls" ) );

        //创建工作表                                     工作表sheet名称        索引值 0表示第一个sheet页索引
            WritableSheet sheet = workbook.createSheet( "用户信息", 0 );

            //设置  表示设置第3列  15代表宽度
            sheet.setColumnView( 2,15 );

            String [] title={"编号","姓名","出生年月"};

            //创建单元格对象
            Label label = null;

            //处理标题栏
            for(int i =0;i<title.length;i++){

                //第一个参数是列索引   第二个参数是行索引  第三个参数是单元格内容
                label = new Label( i,0,title[i] );

                //sheet页添加单元格
                sheet.addCell( label );
            }

            for(int i=1;i<10;i++){
                label = new Label( 0,i,""+i );
                sheet.addCell( label );
                label = new Label( 1,i,"张三"+i );

                sheet.addCell( label );


                //日期格式特殊处理
                DateFormat format = new DateFormat( "yyyy 年MM 月dd 日 HH:mm:ss" );

                WritableCellFormat cellFormat = new WritableCellFormat( format );



                //第一个参数 列索引  第二 行索引  第三 日期内容    第四 日期格式
                sheet.addCell( new DateTime( 2,i,new Date(),cellFormat ) );

            }

            workbook.write();
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


/*    //JXL导入Excel
    @Test
    public void jxlImportExcel(){
        //获取工作簿
        try {
            Workbook workbook = Workbook.getWorkbook( new FileInputStream( "D:\\用户1.xsl" ) );

            //获取sheet页
            Sheet[] sheets = workbook.getSheets();

            //获取第一个sheet
            Sheet sheet = sheets[0];

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }*/

}
