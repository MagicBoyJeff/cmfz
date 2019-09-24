package com.hujie.service;

import com.hujie.entity.Admin;
import com.hujie.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;


    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Admin login(String name, String password) {
        return adminMapper.loginByNameAndPassword( name, password );
    }
}
