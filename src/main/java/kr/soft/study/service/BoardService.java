package kr.soft.study.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.soft.study.dto.board.BoardRegisterDTO;
import kr.soft.study.dto.member.MemberInfoDTO;
import kr.soft.study.mapper.BoardMapper;

@Service
public class BoardService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardMapper boardMapper;

	
	public void register(BoardRegisterDTO boardRegisterDTO, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		//session �ҷ�����
		MemberInfoDTO memberInfo = (MemberInfoDTO) session.getAttribute("userInfo");

		//session ( �α��� ���� ��� )
		if(memberInfo == null) {
			
		}
		logger.info("{}", memberInfo.toString());
		long userIdx = memberInfo.getUserIdx(); 	//userIdx �ҷ�����
		boardRegisterDTO.setUserIdx(userIdx);		//userIdx �����ϱ�
		
		boardMapper.register(boardRegisterDTO);
		
	}
}
