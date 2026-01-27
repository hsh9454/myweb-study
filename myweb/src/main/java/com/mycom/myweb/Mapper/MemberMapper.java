package com.mycom.myweb.Mapper;

import org.apache.ibatis.annotations.Select;

public interface MemberMapper {
    // DB의 member_test 테이블에서 맨 위에 있는 이름 하나를 가져오라는 명령입니다!
	@Select("SELECT USERNAME FROM MEMBER_TEST WHERE ROWNUM = 1")
	String getName();
}