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
	
	// MemberServiceImpl.java 파일 안에 추가할 내용
	@Override
	public void insertTest(int id, String name) {
	    memberMapper.insertTest(id, name);
	}

	@Override
	public void updateTest(int id, String name) {
	    memberMapper.updateTest(id, name);
	}

	@Override
	public void deleteTest(int id) {
	    memberMapper.deleteTest(id);
	}
	
}




