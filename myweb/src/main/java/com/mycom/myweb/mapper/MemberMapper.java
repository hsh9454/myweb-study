package com.mycom.myweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.myweb.UserVO;

@Mapper
public interface MemberMapper {
	public void join(UserVO vo);
	String getName();

	public void insertTest(@Param("id") int id, @Param("name") String name);

	public void updateTest(@Param("id") int id, @Param("name") String name);

	public void deleteTest(@Param("id") int id);
}