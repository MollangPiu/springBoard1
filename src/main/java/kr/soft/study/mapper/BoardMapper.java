package kr.soft.study.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.soft.study.dto.board.BoardRegisterDTO;

@Mapper
public interface BoardMapper {

	/**
	 * �Խ��� �� ����ϱ�
	 * @param boardRegisterDTO
	 */
	void register(BoardRegisterDTO boardRegisterDTO);
}
