package kr.soft.study.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.soft.study.dto.board.BoardListDTO;
import kr.soft.study.dto.board.BoardRegisterDTO;
import kr.soft.study.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardService boardService;
	
	
	@GetMapping("/register")
	public String register() {
		return "board/boardRegister";
	}
	
	@PostMapping("/registerProcess")
	public String registerProcess(BoardRegisterDTO boardRegisterDTO,
			HttpServletRequest request) {
		
		String result = boardService.register(boardRegisterDTO, request);
		logger.info("result: {}", result);
		if(result.equals("sucess")) {
			return "redirect:/board/list";
		}
		return "redirect:/board/register";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		
		List<BoardListDTO> lists = boardService.list();
		logger.info("size: {}", lists.size());
		
		model.addAttribute("lists", lists);
		
		return "board/boardList";
	}

}
