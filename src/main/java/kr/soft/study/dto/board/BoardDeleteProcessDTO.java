package kr.soft.study.dto.board;

public class BoardDeleteProcessDTO {

	private long boardIdx;
	private long userIdx;
	
	
	public long getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(long boardIdx) {
		this.boardIdx = boardIdx;
	}
	public long getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(long userIdx) {
		this.userIdx = userIdx;
	}
}
