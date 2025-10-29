package kr.soft.study.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.soft.study.dto.board.BoardDTO;
import kr.soft.study.dto.board.BoardDeleteProcessDTO;
import kr.soft.study.dto.board.BoardDetailDTO;
import kr.soft.study.dto.board.BoardListDTO;
import kr.soft.study.dto.board.BoardRegisterDTO;
import kr.soft.study.dto.board.BoardUpdateDetailDTO;
import kr.soft.study.dto.board.BoardUpdateProcessDTO;
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
	 * 
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
		String result = checkNull(boardRegisterDTO);
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

		return "sucess:" + String.valueOf(boardLastIdx);

	}

	/**
	 * 게시글 리스트 보기
	 * 
	 * @return
	 */
	public List<BoardListDTO> list() {
		List<BoardListDTO> lists = null;

		lists = boardMapper.list();

		return lists;
	}

	/**
	 * 상세보기
	 * 
	 * @param request
	 * @return
	 */
	public BoardDetailDTO detail(HttpServletRequest request) {

		String strIdx = request.getParameter("idx"); // idx 가져오기
		if (strIdx == null) {
			return null;
		}

		int idx = Integer.parseInt(strIdx);

		boardMapper.viewCount(idx);

		BoardDetailDTO detail = boardMapper.detail(idx);

		return detail;
	}

	/**
	 * 수정하기, 상세보기
	 * 
	 * @param request
	 * @return
	 */
	public BoardUpdateDetailDTO updateDetail(HttpServletRequest request) {

		String strIdx = request.getParameter("idx"); // idx 가져오기
		if (strIdx == null) {
			return null;
		}

		int idx = Integer.parseInt(strIdx);
		BoardUpdateDetailDTO detail = boardMapper.updateDetail(idx);
		
		//비교하기 유효성 검사
		HttpSession session = request.getSession();
		MemberInfoDTO userInfo = (MemberInfoDTO)session.getAttribute("userInfo");
		
		if(userInfo == null) {
			return null;
		}
		
		if(detail.getUserIdx() != userInfo.getUserIdx()) {
			return null;
		}

		return detail;
	}

	/**
	 * 수정하기 완료버튼
	 * @param boardUpdateProcessDTO
	 * @param request
	 * @return
	 */
	public String updateProcess(BoardUpdateProcessDTO boardUpdateProcessDTO, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberInfoDTO info = (MemberInfoDTO) session.getAttribute("userInfo");

		//작성자 유효성 검사
		if (info == null) {
			return "null";
		}

		//작성자 넣기
		long userIdx = info.getUserIdx();
		boardUpdateProcessDTO.setUserIdx(userIdx);

		// 빈값 확인하기
		String result = checkNull(boardUpdateProcessDTO);
		if (!result.equals("sucess")) {
			return result;
		}

		// 자리수 확인하기
		result = checkValidity(boardUpdateProcessDTO);
		if (!result.equals("sucess")) {
			return result;
		}

		boardMapper.updateProcess(boardUpdateProcessDTO);
		
		return "sucess:" + String.valueOf(boardUpdateProcessDTO.getBoardIdx());
	}
	
	/**
	 * 삭제하기
	 * @param request
	 * @return
	 */
	public String deleteProcess(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberInfoDTO info = (MemberInfoDTO) session.getAttribute("userInfo");

		//작성자 유효성 검사
		if (info == null) {
			return "null";
		}
		
		long userIdx = info.getUserIdx();
		
		String strBoardIdx = request.getParameter("idx");
		long boardIdx = Long.parseLong(strBoardIdx);
		
		BoardDeleteProcessDTO boardDeleteProcessDTO = new BoardDeleteProcessDTO();
		boardDeleteProcessDTO.setUserIdx(userIdx);
		boardDeleteProcessDTO.setBoardIdx(boardIdx);
		
		boardMapper.deleteProcess(boardDeleteProcessDTO);
		
		return "sucess";
	}
	
	
	

	/**
	 * 유효성 검사, 자리수
	 * 
	 * @param boardRegisterDTO
	 * @return
	 */
	private String checkValidity(BoardDTO boardDTO) {

		// 1자리 이상, 200자리 이하
		if (boardDTO.getBoardTitle().length() < 1 || boardDTO.getBoardTitle().length() > 200) {
			return "validity_title";
		}
		// 6자리 이상
		if (boardDTO.getBoardContent().length() < 6) {
			return "validity_content";
		}

		return "sucess";
	}

	/**
	 * NULL 혹은 빈값 체크하기
	 * 
	 * @param BoardDTO
	 * @return
	 */
	private String checkNull(BoardDTO boardDTO) {
		if (boardDTO.getBoardTitle() == null || boardDTO.getBoardTitle().trim().equals("")) {
			return "blank_title";
		}
		if (boardDTO.getBoardContent() == null || boardDTO.getBoardContent().trim().equals("")) {
			return "blank_content";
		}
		if (boardDTO.getBoardFavoriteAnimal() == null || boardDTO.getBoardFavoriteAnimal().trim().equals("")) {
			return "blank_favoriteAnimal";
		}

		return "sucess";
	}
}
