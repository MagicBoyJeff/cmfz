package com.hujie.mapper;

import com.hujie.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    Admin loginByNameAndPassword(@Param( "name" ) String name, @Param( "password" ) String password);
}
