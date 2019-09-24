package com.hujie.service;

import com.hujie.entity.City;
import com.hujie.entity.User;
import com.hujie.mapper.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional

public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String, Object> showAllUser(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        //获取当前页
        map.put( "page",page );

        //总条数
        Integer totalCount = userMapper.totalCount();
        map.put( "records",totalCount );

        //总页数
        Integer pageCount = 0;
        if(totalCount % rows!=0){
            pageCount = totalCount/rows+1;
        }else{
            pageCount = totalCount/rows;
        }
        map.put( "total",pageCount );
        //展示数据内容
        List<User> users = userMapper.showAllUser( page, rows );
        map.put( "rows",users );
        return map;
    }

    @Override
    public HashMap<String,Object> updateStatus(User user) {
        HashMap<String, Object> map = new HashMap<>();
        userMapper.updateUser( user );
        return map;
    }

    @Override
    public List<User> showAll() {
        return userMapper.showAll();
    }

    @Override
    public String addUser(User user) {
        String id = UUID.randomUUID().toString();
        //随机获取id
        user.setId( id );
        user.setCreate_date( new Date() );
        //设置盐值
        String password = DigestUtils.md5Hex( user.getPassword() );
        user.setSalt( password );
        user.setStatus( "2" );
        userMapper.addUser( user );
        return user.getId();
    }

    @Override
    public List<Integer> queryBySex(String sex) {
        ArrayList<Integer> list = new ArrayList<>();
        //i表示月份
        for(int i =0;i<=12;i++){
            Integer count = userMapper.queryBySexAndMonth( sex, i );
            list.add( count );
        }
        return list;
    }

    @Override
    public List<City> selectAllCity(String sex) {
        List<City> cities = userMapper.showAllCity( sex );
        return cities;
    }
}
