package com.mycom.myweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	  


}
