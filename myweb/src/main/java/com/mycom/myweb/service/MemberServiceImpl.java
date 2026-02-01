package com.mycom.myweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myweb.mapper.MemberMapper;
import com.mycom.myweb.UserVO;
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public String getDbName() {
		return memberMapper.getName();	
	}

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
	
	@Override
	public void join(UserVO vo) {
	    memberMapper.join(vo); 
	}
	
}




