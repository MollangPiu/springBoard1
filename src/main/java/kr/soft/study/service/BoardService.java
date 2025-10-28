package kr.soft.study.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.soft.study.dto.board.BoardDetailDTO;
import kr.soft.study.dto.board.BoardListDTO;
import kr.soft.study.dto.board.BoardRegisterDTO;
import kr.soft.study.dto.member.MemberInfoDTO;
import kr.soft.study.dto.member.MemberRegisterDTO;
import kr.soft.study.mapper.BoardMapper;

@Service
public class BoardService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BoardMapper boardMapper;

	/**
	 * 회원가입하기
	 * @param boardRegisterDTO
	 * @param request
	 * @return
	 */
	public String register(BoardRegisterDTO boardRegisterDTO, HttpServletRequest request) {
		HttpSession session = request.getSession();

		// session 불러오기
		MemberInfoDTO memberInfo = (MemberInfoDTO) session.getAttribute("userInfo");

		// session ( 로그인 없을 경우 )
		if (memberInfo == null) {
			return "null";
		}
		logger.info("{}", memberInfo.toString());
		long userIdx = memberInfo.getUserIdx(); // userIdx 불러오기
		boardRegisterDTO.setUserIdx(userIdx); // userIdx 삽입하기

		// 빈값 확인하기
		String result = checkRegister(boardRegisterDTO);
		if (!result.equals("sucess")) {
			return result;
		}

		// 자리수 확인하기
		result = checkValidity(boardRegisterDTO);
		if (!result.equals("sucess")) {
			return result;
		}

		boardMapper.register(boardRegisterDTO);
		
		int boardLastIdx = boardMapper.boardLastIdx(userIdx);

		return "sucess:"+String.valueOf(boardLastIdx);

	}
	
	/**
	 * 게시글 리스트 보기
	 * @return
	 */
	public List<BoardListDTO> list() {
		List<BoardListDTO> lists = null;
		
		lists = boardMapper.list();
		
		return lists;
	}
	
	
	/**
	 * 상세보기
	 * @param request
	 * @return
	 */
	public BoardDetailDTO detail(HttpServletRequest request) {
		
		String strIdx = request.getParameter("idx");	//idx 가져오기
		if(strIdx == null) {
			return null;
		}
		
		int idx = Integer.parseInt(strIdx);
		
		boardMapper.viewCount(idx);
		
		BoardDetailDTO detail = boardMapper.detail(idx);
		
		return detail;
	}

	/**
	 * 유효성 검사, 자리수
	 * 
	 * @param boardRegisterDTO
	 * @return
	 */
	private String checkValidity(BoardRegisterDTO boardRegisterDTO) {

		// 1자리 이상, 200자리 이하
		if (boardRegisterDTO.getBoardTitle().length() < 1 || boardRegisterDTO.getBoardTitle().length() > 200) {
			return "validity_title";
		}
		// 6자리 이상
		if (boardRegisterDTO.getBoardContent().length() < 6) {
			return "validity_content";
		}

		return "sucess";
	}

	/**
	 * NULL 혹은 빈값 체크하기
	 * 
	 * @param boardRegisterDTO
	 * @return
	 */
	private String checkRegister(BoardRegisterDTO boardRegisterDTO) {
		if (boardRegisterDTO.getBoardTitle() == null || boardRegisterDTO.getBoardTitle().trim().equals("")) {
			return "blank_title";
		}
		if (boardRegisterDTO.getBoardContent() == null || boardRegisterDTO.getBoardContent().trim().equals("")) {
			return "blank_content";
		}
		if (boardRegisterDTO.getBoardFavoriteAnimal() == null
				|| boardRegisterDTO.getBoardFavoriteAnimal().trim().equals("")) {
			return "blank_favoriteAnimal";
		}

		return "sucess";
	}
}
