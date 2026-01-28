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
	public String insertMember(@RequestParam("id") int id, @RequestParam("name") String name, Model model) {
	    // 1. 여기서 진짜 DB에 넣으라고 일을 시켜야 합니다!
	    memberService.insertTest(id, name); 
	    
	    model.addAttribute("msg", name + " 등록 성공!");
	    return "showResult";
	}

	@GetMapping("/update") // 소문자로 맞추는 게 편합니다! 
	public String updateMember(@RequestParam("id") int id, @RequestParam("name") String name, Model model) {
	    // 2. 여기서 진짜 DB를 수정하라고 일을 시켜야 합니다!
	    memberService.updateTest(id, name);
	    
	    model.addAttribute("msg", name + " 수정 성공!");
	    return "showResult";
	}

	@GetMapping("/delete") 
	public String deleteMember(@RequestParam("id") int id, Model model) {
	    // 3. 여기서 진짜 DB를 삭제하라고 일을 시켜야 합니다!
	    memberService.deleteTest(id);
	    
	    model.addAttribute("msg", "ID " + id + " 삭제 성공!");
	    return "showResult";
	}
	

}
