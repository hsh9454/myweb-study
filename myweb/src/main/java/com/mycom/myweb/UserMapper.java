package com.mycom.myweb;

import org.apache.ibatis.annotations.Select;


public interface UserMapper {

    
    @Select("SELECT * FROM tbl_user WHERE userid = #{userid} AND userpw = #{userpw}")
    public UserVO login(UserVO vo);
    
}