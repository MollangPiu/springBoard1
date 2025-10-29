package kr.soft.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.soft.study.dto.board.BoardDeleteProcessDTO;
import kr.soft.study.dto.board.BoardDetailDTO;
import kr.soft.study.dto.board.BoardListDTO;
import kr.soft.study.dto.board.BoardRegisterDTO;
import kr.soft.study.dto.board.BoardSearchDTO;
import kr.soft.study.dto.board.BoardUpdateDetailDTO;
import kr.soft.study.dto.board.BoardUpdateProcessDTO;

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
	
	
	
	int listSize(BoardSearchDTO boardSearchDTO);
	
	
	int listSearchAnimalSize(BoardSearchDTO boardSearchDTO);
	
	
	int listSearchKeywordSize(BoardSearchDTO boardSearchDTO);
	
	
	int listSearchAllSize(BoardSearchDTO boardSearchDTO);
	
	
	/**
	 * �Խ��� ����Ʈ
	 * @param boardSearchDTO
	 * @return
	 */
	List<BoardListDTO> list(BoardSearchDTO boardSearchDTO);
	
	/**
	 * �Խ��� �˻�, Ű����, ���� �˻�
	 * @param boardSearchDTO
	 * @return
	 */
	List<BoardListDTO> listSearchAll(BoardSearchDTO boardSearchDTO);
	
	/**
	 * �Խ��� ����Ʈ ���� �˻�
	 * @param boardSearchDTO
	 * @return
	 */
	List<BoardListDTO> listSearchAnimal(BoardSearchDTO boardSearchDTO);
	
	
	/**
	 * �Խ��� ����Ʈ Ű���� �˻�
	 * @param boardSearchDTO
	 * @return
	 */
	List<BoardListDTO> listSearchKeyword(BoardSearchDTO boardSearchDTO);
	
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
	
	/**
	 * �����ϱ�, �󼼺��� ȭ��
	 * @param idx
	 * @return
	 */
	BoardUpdateDetailDTO updateDetail(int idx);
	
	/**
	 * �����ϱ�, �Ϸ��ư
	 * @param boardUpdateProcessDTO
	 */
	void updateProcess(BoardUpdateProcessDTO boardUpdateProcessDTO);
	
	/**
	 * �����ϱ�, �Ϸ��ư
	 * @param boardDeleteProcessDTO
	 */
	void deleteProcess(BoardDeleteProcessDTO boardDeleteProcessDTO);
}
