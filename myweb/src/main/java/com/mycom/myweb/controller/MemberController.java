package com.mycom.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycom.myweb.UserVO;
import com.mycom.myweb.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping("/login")
	public void loginGET() {
		System.out.println("로그인 페이지 진입");
	}

	@GetMapping("/join")
	public void joinGET() {
		System.out.println("회원가입 페이지 진입");
	}

	@PostMapping("/join")
	public String joinPOST(UserVO vo) {
		System.out.println("가입 요청 데이터: " + vo);
		memberService.join(vo);
		return "redirect:/member/login";
	}

	@GetMapping("showData")

	public String showData(Model model) {
		String result = memberService.getDbName();
		model.addAttribute("name", result);
		return "showResult";
	}

	@GetMapping("insert")
	public String insertMember(@RequestParam("id") int id, @RequestParam("name") String name, Model model) {

		memberService.insertTest(id, name);

		model.addAttribute("msg", name + " 등록 성공!");
		return "showResult";
	}

	@GetMapping("update")
	public String updateMember(@RequestParam("id") int id, @RequestParam("name") String name, Model model) {

		memberService.updateTest(id, name);

		model.addAttribute("msg", name + " 수정 성공!");
		return "showResult";
	}

	@GetMapping("delete")
	public String deleteMember(@RequestParam("id") int id, Model model) {

		memberService.deleteTest(id);

		model.addAttribute("msg", "ID " + id + " 삭제 성공!");
		return "showResult";
	}

}
