package kr.soft.study.dto.board;

public class BoardDTO {

	protected long userIdx;
	protected String boardTitle;
	protected String boardFavoriteAnimal;
	protected String boardContent;
	
	
	public long getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(long userIdx) {
		this.userIdx = userIdx;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardFavoriteAnimal() {
		return boardFavoriteAnimal;
	}
	public void setBoardFavoriteAnimal(String boardFavoriteAnimal) {
		this.boardFavoriteAnimal = boardFavoriteAnimal;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
}
