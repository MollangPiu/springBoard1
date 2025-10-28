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
	 * ȸ�������ϱ�
	 * @param boardRegisterDTO
	 * @param request
	 * @return
	 */
	public String register(BoardRegisterDTO boardRegisterDTO, HttpServletRequest request) {
		HttpSession session = request.getSession();

		// session �ҷ�����
		MemberInfoDTO memberInfo = (MemberInfoDTO) session.getAttribute("userInfo");

		// session ( �α��� ���� ��� )
		if (memberInfo == null) {
			return "null";
		}
		logger.info("{}", memberInfo.toString());
		long userIdx = memberInfo.getUserIdx(); // userIdx �ҷ�����
		boardRegisterDTO.setUserIdx(userIdx); // userIdx �����ϱ�

		// �� Ȯ���ϱ�
		String result = checkRegister(boardRegisterDTO);
		if (!result.equals("sucess")) {
			return result;
		}

		// �ڸ��� Ȯ���ϱ�
		result = checkValidity(boardRegisterDTO);
		if (!result.equals("sucess")) {
			return result;
		}

		boardMapper.register(boardRegisterDTO);
		
		int boardLastIdx = boardMapper.boardLastIdx(userIdx);

		return "sucess:"+String.valueOf(boardLastIdx);

	}
	
	/**
	 * �Խñ� ����Ʈ ����
	 * @return
	 */
	public List<BoardListDTO> list() {
		List<BoardListDTO> lists = null;
		
		lists = boardMapper.list();
		
		return lists;
	}
	
	
	/**
	 * �󼼺���
	 * @param request
	 * @return
	 */
	public BoardDetailDTO detail(HttpServletRequest request) {
		
		String strIdx = request.getParameter("idx");	//idx ��������
		if(strIdx == null) {
			return null;
		}
		
		int idx = Integer.parseInt(strIdx);
		
		boardMapper.viewCount(idx);
		
		BoardDetailDTO detail = boardMapper.detail(idx);
		
		return detail;
	}

	/**
	 * ��ȿ�� �˻�, �ڸ���
	 * 
	 * @param boardRegisterDTO
	 * @return
	 */
	private String checkValidity(BoardRegisterDTO boardRegisterDTO) {

		// 1�ڸ� �̻�, 200�ڸ� ����
		if (boardRegisterDTO.getBoardTitle().length() < 1 || boardRegisterDTO.getBoardTitle().length() > 200) {
			return "validity_title";
		}
		// 6�ڸ� �̻�
		if (boardRegisterDTO.getBoardContent().length() < 6) {
			return "validity_content";
		}

		return "sucess";
	}

	/**
	 * NULL Ȥ�� �� üũ�ϱ�
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
