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
	 * 회원가입 진행하기
	 * @param memberRegisterDTO
	 * @return
	 */
	public String register(MemberRegisterDTO memberRegisterDTO) {
		String result = null; //결과 값
		
		//빈값 체크하기
		result = checkRegister(memberRegisterDTO); // 빈값 체크하기
		if(!result.equals("sucess")) {
			return result;
		}
		
		//자리수 검사
		result = checkValidity(memberRegisterDTO);
		if(!result.equals("sucess")) {
			return result;
		}
		
		//중복 검사
		int checkId = memberMapper.checkId(memberRegisterDTO.getUserId());
		if(checkId > 0) {
			return "not unique id";
		}
		int checkNickName = memberMapper.checkNickName(memberRegisterDTO.getUserNickName());
		if(checkNickName >0 ) {
			return "not unique nickName";
		}
		
		//등록
		memberMapper.register(memberRegisterDTO);
		
		return "sucess";
	}
	
	
	public boolean login(HttpServletRequest request, MemberLoginDTO memberLoginDTO) {
		
		MemberInfoDTO test = null;
		
		String dbPassword = memberMapper.login(memberLoginDTO.getUserId());
		
		//일치 된 아이디 없음
		if(dbPassword == null) {
			return false;
		}
		
		//비밀번호 일치 실패
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
	 * 유효성 검사, 자리수
	 * @param memberRegisterDTO
	 * @return
	 */
	private String checkValidity(MemberRegisterDTO memberRegisterDTO) {
		//4자리 이상, 15자리 이하
		if(memberRegisterDTO.getUserId().length() < 4 || memberRegisterDTO.getUserId().length() > 15) {
			return "validity_id";
		}
		//6자리 이상, 30자리 이하
		if(memberRegisterDTO.getUserPw().length() < 6 || memberRegisterDTO.getUserPw().length() > 30) {
			return "validity_pw";
		}
		//3자리 이상, 18자리 이하
		if(memberRegisterDTO.getUserNickName().length() < 4 || memberRegisterDTO.getUserNickName().length() > 18) {
			return "validity_nickname";
		}
		
		return "sucess";
	}
	
	
	
	/**
	 * NULL 혹은 빈값 체크하기
	 * @param memberRegisterDTO 회원가입 DTO
	 * @return 성공: sucess
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
