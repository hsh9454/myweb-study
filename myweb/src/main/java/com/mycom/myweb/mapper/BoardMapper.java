package com.mycom.myweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.mycom.myweb.BoardVO;
import com.mycom.myweb.Criteria;

@Mapper
public interface BoardMapper {
	 public List<BoardVO> getList();
	 public List<BoardVO> getListWithPaging(Criteria cri);
	 public void insert(BoardVO vo);
	 public BoardVO get(int bno);
	 public int update(BoardVO vo);
	 public int delete(int bno);
	 public int getTotalCount(Criteria cri);
}


    
 