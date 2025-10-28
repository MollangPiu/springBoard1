package kr.soft.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.soft.study.dto.board.BoardListDTO;
import kr.soft.study.dto.board.BoardRegisterDTO;

@Mapper
public interface BoardMapper {

	/**
	 * 게시판 글 등록하기
	 * @param boardRegisterDTO
	 */
	void register(BoardRegisterDTO boardRegisterDTO);
	
	/**
	 * 게시판 리스트
	 * @return
	 */
	List<BoardListDTO> list();
}
