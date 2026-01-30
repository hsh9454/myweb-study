package com.mycom.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycom.myweb.BoardVO;
import com.mycom.myweb.Criteria;
import com.mycom.myweb.UserVO;
import com.mycom.myweb.mapper.UserMapper;
import com.mycom.myweb.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	@Qualifier("boardServiceImpl")
	private BoardService service;
	
	@Autowired
    private UserMapper userMapper;

    @GetMapping("/list")
    public void list(Criteria cri, Model model) {
        
        System.out.println("--- 게시판 목록 페이지 접속 ---");
        System.out.println("요청 페이지: " + cri.getPageNum());
        
        int total = service.getTotalCount();
        int totalPages = (int) Math.ceil((double) total / cri.getAmount());
        		
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", cri.getPageNum());
        
        
        
        model.addAttribute("list", service.getListWithPaging(cri));
        model.addAttribute("pageMaker", cri); 
    }
    

    @GetMapping("/get")
    public void get(@RequestParam("bno") int bno, Model model) {
        
        System.out.println(bno + "번 글 상세보기 요청 들어옴!");
        
        
        BoardVO vo = service.get(bno);
        model.addAttribute("board", vo);
        
      
    }
    
    
 
    @GetMapping("/modify")
    public void modify(@RequestParam("bno") int bno, Model model) {
        model.addAttribute("board", service.get(bno));
    }

  
    @PostMapping("/modify")
    public String modify(BoardVO vo) {
    	service.update(vo);
        return "redirect:/board/list"; 
    }

 
    @PostMapping("/remove")
    public String remove(@RequestParam("bno") int bno) {
    	service.delete(bno);
        return "redirect:/board/list"; 
    }
    

    @GetMapping("/register")
    public String register() {
        return "board/register";
    }

    
    @PostMapping("/register")
    public String register(BoardVO vo) {
        System.out.println("새 글 등록 요청: " + vo.getTitle());
        
        service.insert(vo); 
        
        return "redirect:/board/list"; 
    }
    
 
    @GetMapping("/login")
    public void login() {
        System.out.println("--- 로그인 페이지 접속 ---");
    }

  
    @PostMapping("/login")
    public String login(UserVO vo, HttpServletRequest request) {
        System.out.println("로그인 시도 아이디: " + vo.getUserid());

   
        UserVO loginUser = userMapper.login(vo);

        if (loginUser != null) {
           
            HttpSession session = request.getSession();
            session.setAttribute("user", loginUser); 
            System.out.println("로그인 성공! : " + loginUser.getUsername());
            return "redirect:/board/list"; 
        } else {
        
            System.out.println("로그인 실패...");
            return "redirect:/board/login";
        }
    }

  
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/board/list";
    }
    
    
    
    
    
    
    
}