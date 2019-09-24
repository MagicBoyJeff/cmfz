package com.hujie.controller;


import com.hujie.entity.Guru;
import com.hujie.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("guru")
public class GuruController {

    @Autowired
    GuruService guruService;

    @RequestMapping("show")
    public Map<String,Object> showAll(Integer page, Integer rows){
        Map<String, Object> map = guruService.showGuru( page, rows );
        return map;
    }


    @RequestMapping("updateGuru")
    public HashMap<String,Object> updateGuru(Guru guru){
        HashMap<String, Object> map = guruService.updateGuru( guru );
        return map;
    }

}
