package com.mycom.myweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycom.myweb.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/showData") 

		public String showData(Model model) {
		String result = memberService.getDbName();
		model.addAttribute("name",result);
		return "showResult";
	}
	
	@GetMapping("/insert") 
	public String insertMember(@RequestParam("name") String name, Model model) {
		model.addAttribute("msg", name + " 등록 시도!");
		return "showResult";
	}
	
	@GetMapping("/Update") 
	public String updateMember(@RequestParam("name") String name, Model model) {
		model.addAttribute("msg", name + " 수정 시도!");
		return "showResult";
	
	}
	
	@GetMapping("/delete") //딜리트
	public String deleteMember(@RequestParam("name") String name, Model model) {
		model.addAttribute("msg", name + " 삭제 시도!");
		return "showResult";
		
	}
	

}
