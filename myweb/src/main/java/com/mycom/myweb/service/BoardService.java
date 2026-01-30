package com.mycom.myweb.service;

import com.mycom.myweb.Criteria;
import java.util.List;
import com.mycom.myweb.BoardVO;


public interface BoardService {
	
	public int getTotalCount();
	public List<BoardVO> getListWithPaging(Criteria cri);
    public List<BoardVO> getList();
    public void insert(BoardVO vo); 
    public BoardVO get(int bno);
    public int update(BoardVO vo);
    public int delete(int bno);

}
