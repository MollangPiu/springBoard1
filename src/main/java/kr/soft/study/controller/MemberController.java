package kr.soft.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.soft.study.dto.member.MemberRegisterDTO;
import kr.soft.study.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/test")
	public void test() {
		String text = memberService.testConnection();
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/registerProcess")
	public String registerProcess(MemberRegisterDTO memberRegisterDTO) {
		
		memberService.register(memberRegisterDTO);
		
		return "login";
	}
}
