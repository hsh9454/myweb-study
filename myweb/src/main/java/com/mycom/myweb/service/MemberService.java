package com.mycom.myweb.service;

public interface MemberService {
	public String getDbName();
	
	void register(int id, String name);
	void modify(int id, String name);
	void remove(int id);
	
	
	
	
	
	
	

}
