package kr.soft.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.soft.study.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/test")
	public void test() {
		String text = memberService.testConnection();
	}
}
