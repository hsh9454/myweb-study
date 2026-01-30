package com.mycom.myweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
   
	String getName();

	public void insertTest(@Param("id") int id, @Param("name") String name);

	public void updateTest(@Param("id") int id, @Param("name") String name);

	public void deleteTest(@Param("id") int id);
}