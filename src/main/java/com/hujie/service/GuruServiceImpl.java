package com.hujie.service;

import com.hujie.entity.Guru;
import com.hujie.mapper.GuruMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional

public class GuruServiceImpl implements GuruService {


    @Autowired
    GuruMapper guruMapper;

    @Override
    public Map<String, Object> showGuru(Integer page, Integer rows) {

        Map<String, Object> map = new HashMap<>();

        //当前页数
        map.put( "page",page );

        //总条数
        Integer totalCount = guruMapper.totalCount();
        map.put( "records",totalCount );

        //总页数
        Integer pageCount = 0;
        if(totalCount%rows!= 0){
            pageCount = totalCount/rows+1;
        }else{
            pageCount= totalCount/rows;
        }
        map.put( "total",pageCount );

        //展示内容
        List<Guru> gurus = guruMapper.showAll( page, rows );
        map.put( "rows",gurus );
        return map;
    }

    @Override
    public HashMap<String, Object> updateGuru(Guru guru) {
        HashMap<String, Object> map = new HashMap<>();
        guruMapper.updateGuru( guru );
        return map;
    }
}
