package com.hujie.controller;


import com.hujie.entity.Admin;
import com.hujie.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @RequestMapping("login")
    public @ResponseBody Map<String ,Object> login(String name, String password, String enCode, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Admin admin = adminService.login( name, password );
        session.setAttribute( "admin",admin );
        String code = (String)session.getAttribute( "code" );
        if(code.equals( enCode )){
            if(name.equals(admin.getName()  )){
                if(password.equals( admin.getPassword() )){
                    map.put( "msg","ok" );
                }else{
                    map.put( "msg","密码错误" );
                }
            }else{
                map.put("msg","用户名错误");
            }
        }else{
            map.put( "msg","验证码错误" );
        }
        return map;
    }

    //管理员退出
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login/login.jsp";
    }
}
