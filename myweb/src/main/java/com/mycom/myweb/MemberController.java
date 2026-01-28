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
	  
	    memberService.insertTest(id, name); 
	    
	    model.addAttribute("msg", name + " 등록 성공!");
	    return "showResult";
	}

	@GetMapping("/update") 
	public String updateMember(@RequestParam("id") int id, @RequestParam("name") String name, Model model) {
	   
	    memberService.updateTest(id, name);
	    
	    model.addAttribute("msg", name + " 수정 성공!");
	    return "showResult";
	}

	@GetMapping("/delete") 
	public String deleteMember(@RequestParam("id") int id, Model model) {
	   
	    memberService.deleteTest(id);
	    
	    model.addAttribute("msg", "ID " + id + " 삭제 성공!");
	    return "showResult";
	}
	

}
