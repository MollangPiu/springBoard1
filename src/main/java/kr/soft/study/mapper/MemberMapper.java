package kr.soft.study.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.soft.study.dto.member.MemberInfoDTO;
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
	
	/**
	 * ȸ��������, �ߺ� ���̵� üũ
	 * @param id
	 * @return
	 */
	public int checkId(String id);
	
	/**
	 * ȸ��������, �ߺ� �г��� üũ
	 * @param nickName
	 * @return
	 */
	public int checkNickName(String nickName);
	
	/**
	 * �α���
	 * @param id ���̵�
	 * @return ��й�ȣ
	 */
	public String login(String id);
	
	/**
	 * ����� ���� ��ȸ
	 * @param id ���̵�
	 * @return ����� ����
	 */
	public MemberInfoDTO userInfo(String id);
}
