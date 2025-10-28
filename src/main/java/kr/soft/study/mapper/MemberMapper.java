package kr.soft.study.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.soft.study.dto.member.MemberInfoDTO;
import kr.soft.study.dto.member.MemberRegisterDTO;

@Mapper
public interface MemberMapper {

	//test
	public String testConnection();
	
	/**
	 * 회원가입 하기
	 * @param memberRegisterDTO
	 */
	public void register(MemberRegisterDTO memberRegisterDTO);
	
	/**
	 * 회원가입전, 중복 아이디 체크
	 * @param id
	 * @return
	 */
	public int checkId(String id);
	
	/**
	 * 회원가입전, 중복 닉네임 체크
	 * @param nickName
	 * @return
	 */
	public int checkNickName(String nickName);
	
	/**
	 * 로그인
	 * @param id 아이디
	 * @return 비밀번호
	 */
	public String login(String id);
	
	/**
	 * 사용자 정보 조회
	 * @param id 아이디
	 * @return 사용자 정보
	 */
	public MemberInfoDTO userInfo(String id);
}
