package com.mycom.myweb.service;
import com.mycom.myweb.UserVO;

public interface MemberService {
	public void join(UserVO vo);  
	
    String getDbName();
    
    void insertTest(int id, String name);
    void updateTest(int id, String name);
    void deleteTest(int id);
}