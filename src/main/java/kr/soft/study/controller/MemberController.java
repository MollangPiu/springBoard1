package kr.soft.study.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.soft.study.dto.member.MemberLoginDTO;
import kr.soft.study.dto.member.MemberRegisterDTO;
import kr.soft.study.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
		
		String result = memberService.register(memberRegisterDTO);
		if(result.equals("sucess")) {
			return "login";
		}
		logger.info("error: {}", result);
		return "redirect:/member/register";
	}
	
	@PostMapping("/loginProcess")
	public String login(MemberLoginDTO memberLoginDTO,
			HttpServletRequest request) {
		
		boolean result = memberService.login(request, memberLoginDTO);
		if(result) {
			return "redirect:/member/login";
		}
		return "login";
	}
}
