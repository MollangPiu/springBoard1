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
	 * ȸ�������ϱ�
	 * 
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
		String result = checkNull(boardRegisterDTO);
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

		return "sucess:" + String.valueOf(boardLastIdx);

	}

	/**
	 * �Խñ� ����Ʈ ����
	 * 
	 * @return
	 */
	public List<BoardListDTO> list() {
		List<BoardListDTO> lists = null;

		lists = boardMapper.list();

		return lists;
	}

	/**
	 * �󼼺���
	 * 
	 * @param request
	 * @return
	 */
	public BoardDetailDTO detail(HttpServletRequest request) {

		String strIdx = request.getParameter("idx"); // idx ��������
		if (strIdx == null) {
			return null;
		}

		int idx = Integer.parseInt(strIdx);

		boardMapper.viewCount(idx);

		BoardDetailDTO detail = boardMapper.detail(idx);

		return detail;
	}

	/**
	 * �����ϱ�, �󼼺���
	 * 
	 * @param request
	 * @return
	 */
	public BoardUpdateDetailDTO updateDetail(HttpServletRequest request) {

		String strIdx = request.getParameter("idx"); // idx ��������
		if (strIdx == null) {
			return null;
		}

		int idx = Integer.parseInt(strIdx);
		BoardUpdateDetailDTO detail = boardMapper.updateDetail(idx);
		
		//���ϱ� ��ȿ�� �˻�
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
	 * �����ϱ� �Ϸ��ư
	 * @param boardUpdateProcessDTO
	 * @param request
	 * @return
	 */
	public String updateProcess(BoardUpdateProcessDTO boardUpdateProcessDTO, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberInfoDTO info = (MemberInfoDTO) session.getAttribute("userInfo");

		//�ۼ��� ��ȿ�� �˻�
		if (info == null) {
			return "null";
		}

		//�ۼ��� �ֱ�
		long userIdx = info.getUserIdx();
		boardUpdateProcessDTO.setUserIdx(userIdx);

		// �� Ȯ���ϱ�
		String result = checkNull(boardUpdateProcessDTO);
		if (!result.equals("sucess")) {
			return result;
		}

		// �ڸ��� Ȯ���ϱ�
		result = checkValidity(boardUpdateProcessDTO);
		if (!result.equals("sucess")) {
			return result;
		}

		boardMapper.updateProcess(boardUpdateProcessDTO);
		
		return "sucess:" + String.valueOf(boardUpdateProcessDTO.getBoardIdx());
	}
	
	/**
	 * �����ϱ�
	 * @param request
	 * @return
	 */
	public String deleteProcess(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberInfoDTO info = (MemberInfoDTO) session.getAttribute("userInfo");

		//�ۼ��� ��ȿ�� �˻�
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
	 * ��ȿ�� �˻�, �ڸ���
	 * 
	 * @param boardRegisterDTO
	 * @return
	 */
	private String checkValidity(BoardDTO boardDTO) {

		// 1�ڸ� �̻�, 200�ڸ� ����
		if (boardDTO.getBoardTitle().length() < 1 || boardDTO.getBoardTitle().length() > 200) {
			return "validity_title";
		}
		// 6�ڸ� �̻�
		if (boardDTO.getBoardContent().length() < 6) {
			return "validity_content";
		}

		return "sucess";
	}

	/**
	 * NULL Ȥ�� �� üũ�ϱ�
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
