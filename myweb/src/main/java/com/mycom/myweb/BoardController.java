package com.mycom.myweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.mycom.myweb.UserMapper;
import com.mycom.myweb.UserVO;

@Controller
public class BoardController {

   
	@Autowired  
	private BoardMapper mapper;
	
	@Autowired
    private UserMapper userMapper;

    
    @GetMapping("/board/list")
    public void list(Criteria cri, Model model) {
        
        System.out.println("--- 게시판 목록 페이지 접속 ---");
        System.out.println("요청 페이지: " + cri.getPageNum());
        
        int total = mapper.getTotalCount();
        int totalPages = (int) Math.ceil((double) total / cri.getAmount());
        		
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", cri.getPageNum());
        
        
        
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
        
        return "redirect:/board/list"; 
    }
    
 
    @GetMapping("/board/login")
    public void login() {
        System.out.println("--- 로그인 페이지 접속 ---");
    }

  
    @PostMapping("/board/login")
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

  
    @GetMapping("/board/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/board/list";
    }
    
    
    
    
    
    
    
}