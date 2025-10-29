package kr.soft.study.dto.board;

public class BoardUpdateProcessDTO extends BoardDTO{

	private int boardIdx;

	public int getBoardIdx() {
		return boardIdx;
	}

	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}

	@Override
	public String toString() {
		return "BoardUpdateProcessDTO [boardIdx=" + boardIdx + ", userIdx=" + userIdx + ", boardTitle=" + boardTitle
				+ ", boardFavoriteAnimal=" + boardFavoriteAnimal + ", boardContent=" + boardContent + "]";
	}
	
}
