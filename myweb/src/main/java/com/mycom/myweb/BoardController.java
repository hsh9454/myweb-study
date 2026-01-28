package com.mycom.myweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class BoardController {

   
    private BoardMapper mapper = new BoardMapper();

    
    @GetMapping("/board/list")
    public void list(Criteria cri, Model model) {
        
        System.out.println("--- 게시판 목록 페이지 접속 ---");
        System.out.println("요청 페이지: " + cri.getPageNum());
        
        
        model.addAttribute("list", mapper.getListWithPaging(cri));
        
       
        model.addAttribute("pageMaker", cri); 
    }
    

    @GetMapping("/board/get")
    public void get(@RequestParam("bno") int bno, Model model) {
        
        System.out.println(bno + "번 글 상세보기 요청 들어옴!");
        
        
        BoardVO vo = mapper.get(bno);
        model.addAttribute("board", vo);
        
      
    }
    
    
 
    @GetMapping("/board/modify")
    public void modify(@RequestParam("bno") int bno, Model model) {
        model.addAttribute("board", mapper.get(bno));
    }

  
    @PostMapping("/board/modify")
    public String modify(BoardVO vo) {
        mapper.update(vo);
        return "redirect:/board/list"; 
    }

 
    @PostMapping("/board/remove")
    public String remove(@RequestParam("bno") int bno) {
        mapper.delete(bno);
        return "redirect:/board/list"; 
    }
    
    
    

    @GetMapping("/board/register")
    public void register() {
        
    }

    
    @PostMapping("/board/register")
    public String register(BoardVO vo) {
        System.out.println("새 글 등록 요청: " + vo.getTitle());
        
        mapper.insert(vo); 
        
        return "redirect:/board/list"; // 
    }
    
    
    
    
    
    
    
    
    
}