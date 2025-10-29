package kr.soft.study.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.soft.study.dto.board.BoardDetailDTO;
import kr.soft.study.dto.board.BoardListDTO;
import kr.soft.study.dto.board.BoardRegisterDTO;
import kr.soft.study.dto.member.MemberInfoDTO;
import kr.soft.study.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardService boardService;
	
	
	@GetMapping("/register")
	public String register(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberInfoDTO memberInfo = (MemberInfoDTO)session.getAttribute("userInfo");
		
		//로그인이 안 되어있을 경우, 리스트 페이지 이동
		if(memberInfo == null) {
			return "redirect:/member/login";
		}
		
		return "board/boardRegister";
	}
	
	@PostMapping("/registerProcess")
	public String registerProcess(BoardRegisterDTO boardRegisterDTO,
			HttpServletRequest request) {
		
		String result = boardService.register(boardRegisterDTO, request);
		logger.info("result: {}", result);
		if(result.startsWith("sucess:")) {
			int index = result.indexOf(":")+1;
			return "redirect:/board/detail?idx="+result.substring(index);
		}
		return "redirect:/board/register";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		
		List<BoardListDTO> lists = boardService.list();
		logger.info("List 크기: {}", lists.size());
		
		model.addAttribute("lists", lists);
		
		return "board/boardList";
	}
	
	@GetMapping("/detail")
	public String detail(Model model, HttpServletRequest request) {
		
		BoardDetailDTO detail = boardService.detail(request);
		if(detail == null) {
			return "redirect:/board/list";
		}
		
		model.addAttribute("detail", detail);
		
		return "board/boardDetail";
	}
	
	@GetMapping("/update")
	public String update() {
		return "board/boardUpdate";
	}

}
