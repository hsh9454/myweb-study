package com.mycom.myweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select; 

@Mapper
public interface TimeMapper {
    
    @Select("SELECT sysdate FROM dual")
    public String getTime();

}


