package com.mycom.myweb.Mapper;

import org.apache.ibatis.annotations.Select; 

public interface TimeMapper {
    
    @Select("SELECT sysdate FROM dual")
    public String getTime();

}


