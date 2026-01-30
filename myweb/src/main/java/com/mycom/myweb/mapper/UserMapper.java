package com.mycom.myweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mycom.myweb.UserVO;


@Mapper
public interface UserMapper {

    
    @Select("SELECT * FROM tbl_user WHERE userid = #{userid} AND userpw = #{userpw}")
    public UserVO login(UserVO vo);
    
}