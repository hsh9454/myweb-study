package com.mycom.myweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myweb.Mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public String getDbName() {
		return memberMapper.getName();
		
	}
	
	@Override
	public void register(int id, String name) {
		memberMapper.insertTest(id, name);
	}
	
	@Override
	public void modify(int id, String name) {
		memberMapper.updateTest(id, name);
	}
	
	@Override
	public void remove(int id) {
		memberMapper.deleteTest(id);
	}
	
}




