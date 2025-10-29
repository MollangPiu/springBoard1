package kr.soft.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.soft.study.dto.board.BoardDetailDTO;
import kr.soft.study.dto.board.BoardListDTO;
import kr.soft.study.dto.board.BoardRegisterDTO;
import kr.soft.study.dto.board.BoardUpdateDetailDTO;

@Mapper
public interface BoardMapper {

	/**
	 * �Խ��� �� ����ϱ�
	 * @param boardRegisterDTO
	 */
	void register(BoardRegisterDTO boardRegisterDTO);
	
	/**
	 * ������ IDX Ȯ���ϱ�
	 * @param idx
	 * @return
	 */
	int boardLastIdx(long idx);
	
	/**
	 * �Խ��� ����Ʈ
	 * @return
	 */
	List<BoardListDTO> list();
	
	/**
	 * ��ȸ�� �ø���
	 * @param idx
	 * @return
	 */
	int viewCount(int idx);
	
	/**
	 * �Խ��� �󼼺���
	 * @param idx
	 * @return
	 */
	BoardDetailDTO detail(int idx);
	
	
	BoardUpdateDetailDTO boardUpdateDetail(int idx);
}
