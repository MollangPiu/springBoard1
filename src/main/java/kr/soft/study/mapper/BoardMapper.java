package kr.soft.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.soft.study.dto.board.BoardDetailDTO;
import kr.soft.study.dto.board.BoardListDTO;
import kr.soft.study.dto.board.BoardRegisterDTO;
import kr.soft.study.dto.board.BoardUpdateDetailDTO;
import kr.soft.study.dto.board.BoardUpdateProcessDTO;

@Mapper
public interface BoardMapper {

	/**
	 * 게시판 글 등록하기
	 * @param boardRegisterDTO
	 */
	void register(BoardRegisterDTO boardRegisterDTO);
	
	/**
	 * 마지막 IDX 확인하기
	 * @param idx
	 * @return
	 */
	int boardLastIdx(long idx);
	
	/**
	 * 게시판 리스트
	 * @return
	 */
	List<BoardListDTO> list();
	
	/**
	 * 조회수 늘리기
	 * @param idx
	 * @return
	 */
	int viewCount(int idx);
	
	/**
	 * 게시판 상세보기
	 * @param idx
	 * @return
	 */
	BoardDetailDTO detail(int idx);
	
	/**
	 * 수정하기, 상세보기 화면
	 * @param idx
	 * @return
	 */
	BoardUpdateDetailDTO updateDetail(int idx);
	
	/**
	 * 수정하기, 완료버튼
	 * @param boardUpdateProcessDTO
	 */
	void updateProcess(BoardUpdateProcessDTO boardUpdateProcessDTO);
}
