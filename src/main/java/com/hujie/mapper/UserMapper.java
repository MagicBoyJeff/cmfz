package com.hujie.mapper;

import com.hujie.entity.City;
import com.hujie.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> showAllUser(@Param( "page" )Integer page,@Param( "rows" )Integer rows);

    Integer totalCount();

    void addUser(User user);

    int updateUser(User user);

    void deleteUser(String[] id);

    List<User> showAll();

    //通过性别和月份展示注册人数
    Integer queryBySexAndMonth(@Param("sex") String sex,@Param("months")Integer months);

    //查询各个城市注册的性别人数
    List<City> showAllCity(String sex);



}
