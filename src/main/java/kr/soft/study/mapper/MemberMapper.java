package kr.soft.study.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.soft.study.dto.member.MemberRegisterDTO;

@Mapper
public interface MemberMapper {

	//test
	public String testConnection();
	
	/**
	 * ȸ������ �ϱ�
	 * @param memberRegisterDTO
	 */
	public void register(MemberRegisterDTO memberRegisterDTO);
}
