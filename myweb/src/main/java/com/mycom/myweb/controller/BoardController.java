package com.mycom.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        int total = service.getTotalCount();
        int totalPages = (int) Math.ceil((double) total / cri.getAmount());
        
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", cri.getPageNum());
        model.addAttribute("list", service.getListWithPaging(cri));
        model.addAttribute("pageMaker", cri); 
        model.addAttribute("total", total);
    }
    
    @GetMapping("/get")
    public void get(@RequestParam("bno") int bno, 
                    @RequestParam(value="num", required=false, defaultValue="0") int num, 
                    Model model) {           
        model.addAttribute("board", service.get(bno));
        model.addAttribute("vNum", num);
    }

    @GetMapping("/modify")
    public void modify(@RequestParam("bno") int bno, 
    		           @RequestParam(value="num", required=false, defaultValue="0") int num,
    		           Model model) {
        model.addAttribute("board", service.get(bno));
        model.addAttribute("vNum", num);
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
    public String register(HttpSession session, RedirectAttributes ra) {
    	if (session.getAttribute("user") == null) {
    		ra.addFlashAttribute("msg", "로그인 후 이용 가능한 서비스입니다.");
    		return "redirect:/board/login";
    	}
    	return "board/register";
    }

    @PostMapping("/register")
    public String register(BoardVO vo) {
        service.insert(vo);
        return "redirect:/board/list"; 
    }

    @GetMapping("/login")
    public void login() { }

    @PostMapping("/login")
    public String login(UserVO vo, HttpServletRequest request) {
        UserVO loginUser = userMapper.login(vo);
        if (loginUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", loginUser); 
            return "redirect:/board/list"; 
        } else {
            return "redirect:/board/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/board/list";
    }
}