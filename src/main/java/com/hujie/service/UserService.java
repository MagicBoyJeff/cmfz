package com.hujie.service;

import com.hujie.entity.City;
import com.hujie.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String,Object> showAllUser(Integer page,Integer rows);

    HashMap<String,Object> updateStatus(User user);

    List<User> showAll();

    String addUser(User user);

    List<Integer> queryBySex(String sex);

    List<City> selectAllCity(String sex);
}
