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
		PageDTO pageMaker = new PageDTO(cri, total);

		model.addAttribute("list", service.getListWithPaging(cri));
		model.addAttribute("pageMaker", pageMaker);
	}

	@GetMapping("/get")
	public void get(@RequestParam("bno") int bno,
			@RequestParam(value = "num", required = false, defaultValue = "0") int num, Model model) {
		model.addAttribute("board", service.get(bno));
		model.addAttribute("vNum", num);
	}

	@GetMapping("/modify")
	public String modify(@RequestParam("bno") int bno,
			@RequestParam(value = "num", required = false, defaultValue = "0") int num, HttpSession session,
			RedirectAttributes ra, Model model) {
		if (session.getAttribute("user") == null) {
			ra.addFlashAttribute("msg", "수정 권한이 없습니다. 로그인해주세요.");
			return "redirect:/board/login";
		}
		model.addAttribute("board", service.get(bno));
		model.addAttribute("vNum", num);
		return "board/modify";
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("bno") int bno, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/board/login";
		}
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
	public String register(BoardVO vo, HttpSession session) {

		UserVO loginUser = (UserVO) session.getAttribute("user");

		if (loginUser != null) {
			vo.setWriter(loginUser.getUsername());
		}
		service.insert(vo);
		return "redirect:/board/list";
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