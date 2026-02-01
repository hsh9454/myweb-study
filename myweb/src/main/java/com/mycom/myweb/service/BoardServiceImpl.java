package com.mycom.myweb.service;

import com.mycom.myweb.Criteria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycom.myweb.BoardVO;
import com.mycom.myweb.mapper.BoardMapper;


@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getList() {
		 return mapper.getList();
	}
	
	@Override
	public void insert(BoardVO vo) {
		 mapper.insert(vo);
	}
	
	@Override
	public BoardVO get(int bno) {
		 return mapper.get(bno);
	}
	
	@Override
	public int update(BoardVO vo) {
		 return mapper.update(vo);
	}
	
	@Override
	public int delete(int bno) {
		 return mapper.delete(bno);
	}
	
	@Override
    public List<BoardVO> getListWithPaging(Criteria cri) {
        return mapper.getListWithPaging(cri);
    }
	
	@Override
	public int getTotalCount(Criteria cri) {
		 return mapper.getTotalCount(cri);
	}
	
	
	
	
	
	
	
}

