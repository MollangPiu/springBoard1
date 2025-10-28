package kr.soft.study.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.soft.study.dto.member.MemberInfoDTO;
import kr.soft.study.dto.member.MemberLoginDTO;
import kr.soft.study.dto.member.MemberRegisterDTO;
import kr.soft.study.mapper.MemberMapper;

@Service
public class MemberService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MemberMapper memberMapper;
	
	public String testConnection() {
		return memberMapper.testConnection();
	}
	
	/**
	 * ȸ������ �����ϱ�
	 * @param memberRegisterDTO
	 * @return
	 */
	public String register(MemberRegisterDTO memberRegisterDTO) {
		String result = null; //��� ��
		
		//�� üũ�ϱ�
		result = checkRegister(memberRegisterDTO); // �� üũ�ϱ�
		if(!result.equals("sucess")) {
			return result;
		}
		
		//�ڸ��� �˻�
		result = checkValidity(memberRegisterDTO);
		if(!result.equals("sucess")) {
			return result;
		}
		
		//�ߺ� �˻�
		int checkId = memberMapper.checkId(memberRegisterDTO.getUserId());
		if(checkId > 0) {
			return "not unique id";
		}
		int checkNickName = memberMapper.checkNickName(memberRegisterDTO.getUserNickName());
		if(checkNickName >0 ) {
			return "not unique nickName";
		}
		
		//���
		memberMapper.register(memberRegisterDTO);
		
		return "sucess";
	}
	
	
	public boolean login(HttpServletRequest request, MemberLoginDTO memberLoginDTO) {
		
		MemberInfoDTO test = null;
		
		String dbPassword = memberMapper.login(memberLoginDTO.getUserId());
		
		//��ġ �� ���̵� ����
		if(dbPassword == null) {
			return false;
		}
		
		//��й�ȣ ��ġ ����
		if(!dbPassword.equals(memberLoginDTO.getUserPw())) {
			return false;
		}
		logger.info("check: {}", memberLoginDTO.getUserId());
		MemberInfoDTO info = memberMapper.userInfo(memberLoginDTO.getUserId());
		
		logger.info("result: {}", info.toString());
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", info);
		
		logger.info("final");
		
		return true;
	}
	
	
	
	/**
	 * ��ȿ�� �˻�, �ڸ���
	 * @param memberRegisterDTO
	 * @return
	 */
	private String checkValidity(MemberRegisterDTO memberRegisterDTO) {
		//4�ڸ� �̻�, 15�ڸ� ����
		if(memberRegisterDTO.getUserId().length() < 4 || memberRegisterDTO.getUserId().length() > 15) {
			return "validity_id";
		}
		//6�ڸ� �̻�, 30�ڸ� ����
		if(memberRegisterDTO.getUserPw().length() < 6 || memberRegisterDTO.getUserPw().length() > 30) {
			return "validity_pw";
		}
		//3�ڸ� �̻�, 18�ڸ� ����
		if(memberRegisterDTO.getUserNickName().length() < 4 || memberRegisterDTO.getUserNickName().length() > 18) {
			return "validity_nickname";
		}
		
		return "sucess";
	}
	
	
	
	/**
	 * NULL Ȥ�� �� üũ�ϱ�
	 * @param memberRegisterDTO ȸ������ DTO
	 * @return ����: sucess
	 */
	private String checkRegister(MemberRegisterDTO memberRegisterDTO) {
		if(memberRegisterDTO.getUserId() != null && memberRegisterDTO.getUserId().trim().equals("")) {
			return "blank_id";
		}
		
		if(memberRegisterDTO.getUserPw() != null && memberRegisterDTO.getUserPw().trim().equals("")) {
			return "blank_pw";
		}
		if(memberRegisterDTO.getUserNickName() != null && memberRegisterDTO.getUserNickName().trim().equals("")) {
			return "blank_nickname";
		}
		
		return "sucess";
	}
}
