package com.hujie.cmfz;

import io.goeasy.GoEasy;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;


@SpringBootTest
@RunWith(SpringRunner.class)
public class testgoeasy {


    @Test
    public void goEasy(){
        //设置必要参数
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-4f8cfd5fff6741c6b58dc71193c5b393");

        //发布消息
        goEasy.publish("cmfz_jeff","Hello, GoEasy--hello world!");
    }


    @Test
    public void testGoEasyCharts(){
        while(true){

            Random random = new Random();

            int[] a={random.nextInt(10),random.nextInt(15),random.nextInt(20),random.nextInt(30),random.nextInt(10)};

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", a);
            String s = jsonObject.toJSONString();

            // 参数：服务器地址    ,  AppKey:commonKey
            GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-4f8cfd5fff6741c6b58dc71193c5b393");

            //参数：管道标识，发送内容
            goEasy.publish("my_channel",s);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
