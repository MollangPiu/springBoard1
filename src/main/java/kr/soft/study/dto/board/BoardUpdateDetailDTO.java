package kr.soft.study.dto.board;

public class BoardUpdateDetailDTO {

	private int boardIdx;
	private String boardTitle;
	private String boardContent;
	private String boardFavoriteAnimal;
	private long userIdx;
	
	
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardFavoriteAnimal() {
		return boardFavoriteAnimal;
	}
	public void setBoardFavoriteAnimal(String boardFavoriteAnimal) {
		this.boardFavoriteAnimal = boardFavoriteAnimal;
	}
	public long getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(long userIdx) {
		this.userIdx = userIdx;
	}
}
