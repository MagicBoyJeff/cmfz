package com.hujie.controller;

import com.hujie.util.ValidateImageCodeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("code")
public class CodeController {

    @RequestMapping("getCode")
    public void getCode(HttpSession session, HttpServletResponse response){
        //1,绘制图片中的数字
        String securityCode = ValidateImageCodeUtils.getSecurityCode();//4位数
        session.setAttribute( "code",securityCode );
        //绘制验证码图片
        BufferedImage image = ValidateImageCodeUtils.createImage( securityCode );

        //将图片写出
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            //参数1 图片  参数2 格式  参数3  流的形式
            ImageIO.write( image,"png",stream );
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
