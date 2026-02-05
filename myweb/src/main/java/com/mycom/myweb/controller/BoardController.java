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
import com.mycom.myweb.PageDTO;
import com.mycom.myweb.UserVO;
import com.mycom.myweb.mapper.MenuMapper;
import com.mycom.myweb.mapper.UserMapper;
import com.mycom.myweb.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	@Qualifier("boardServiceImpl")
	private BoardService service;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
    private MenuMapper menuMapper;

	@GetMapping("/list")
	public void list(Criteria cri, Model model, @RequestParam(value="bgno", defaultValue="1") int bgno) {
		cri.setBgno(bgno);
		model.addAttribute("menuList", menuMapper.selectMenuList());
		int total = service.getTotalCount(cri);
		PageDTO pageMaker = new PageDTO(cri, total);       
		model.addAttribute("list", service.getListWithPaging(cri));
		model.addAttribute("pageMaker", pageMaker);
	}

	@GetMapping("/get")
	public void get(@RequestParam("bno") int bno,
			        @RequestParam(value = "bgno", required = false, defaultValue = "1") int bgno,
		         	@RequestParam(value = "num", required = false, defaultValue = "0") int num, Criteria cri, Model model) {
		
		model.addAttribute("board", service.get(bno));
		model.addAttribute("vNum", num);
		model.addAttribute("cri", cri);
		model.addAttribute("bgno", bgno);
	}

	@GetMapping("/modify")
	public String modify(@RequestParam("bno") int bno,
			@RequestParam(value = "num", required = false, defaultValue = "0") int num, HttpSession session,
			RedirectAttributes ra, Model model) {
		
		model.addAttribute("board", service.get(bno));
		model.addAttribute("vNum", num);
		return "board/modify";
	}

	@PostMapping("/remove")
	public String remove(
	        @RequestParam("bno") int bno, 
	        @RequestParam(value="bgno", required=false, defaultValue="2") int bgno, 
	        @RequestParam("writer") String writer, 
	        HttpSession session) {	
	    
	    UserVO loginUser = (UserVO) session.getAttribute("user");

	    if (loginUser == null || !loginUser.getUsername().equals(writer)) {
	        return "redirect:/board/list?bgno=" + bgno;
	    }
	    service.delete(bno);
	    return "redirect:/board/list?bgno=" + bgno;
	}
	
	
	@GetMapping("/register")
	public String register() {
		return "board/register";
	}

	@PostMapping("/register")
	public String register(BoardVO vo, HttpSession session, RedirectAttributes rttr) {

		UserVO loginUser = (UserVO) session.getAttribute("user");

		if (loginUser != null) {
			vo.setWriter(loginUser.getUsername());
		}
		service.insert(vo);
		rttr.addAttribute("bgno", vo.getBgno());
		return "redirect:/board/list?bgno=" + vo.getBgno();
	}

	
	
	
	
	@GetMapping("/login")
	public void login() {
	}

	@PostMapping("/login")
	public String login(UserVO vo, HttpServletRequest request) {
		UserVO loginUser = userMapper.login(vo);
		if (loginUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", loginUser);
			return "redirect:/board/list?bgno=2";
		} else {
			return "redirect:/board/login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/board/list?bgno=2";
	}
	

	@PostMapping("/uploadPhoto")
    public void uploadPhoto(HttpServletRequest request, HttpServletResponse response) {
        try {
        
            String fileName = request.getHeader("file-name");
            String fileDirectory = request.getServletContext().getRealPath("/resources/upload");
            
            File dir = new File(fileDirectory);
            if (!dir.exists()) dir.mkdirs();
          
            File file = new File(fileDirectory, fileName);
            
            InputStream is = request.getInputStream();
            OutputStream os = new FileOutputStream(file);
            
            int numRead;
            byte[] b = new byte[Integer.parseInt(request.getHeader("file-size"))];
            while ((numRead = is.read(b, 0, b.length)) != -1) {
                os.write(b, 0, numRead);
            }
            
            if (is != null) is.close();
            os.flush();
            os.close();

            String fileURL = request.getContextPath() + "/resources/upload/" + fileName;
            
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print("bNewLine=true&sFileName=" + fileName + "&sFileURL=" + fileURL);
            out.flush();
            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
}






