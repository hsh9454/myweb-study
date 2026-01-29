package com.mycom.myweb.Mapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MemberMapper {
   
	@Select("SELECT USERNAME FROM MEMBER_TEST WHERE ROWNUM = 1")
	String getName();


	
	@Insert("INSERT INTO test (id, name) VALUES (#{id}, #{name})") 
	public void insertTest(@Param("id") int id, @Param("name") String name);

	
	@Update("UPDATE test SET name = #{name} WHERE id = #{id}") 
	public void updateTest(@Param("id") int id, @Param("name") String name);

	@Delete("DELETE FROM test WHERE id = #{id}")
	public void deleteTest(@Param("id") int id);
}