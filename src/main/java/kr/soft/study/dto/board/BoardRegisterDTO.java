package kr.soft.study.dto.board;

public class BoardRegisterDTO {
	
	private long userIdx;
	private String boardTitle;
	private String boardFavoriteAnimal;
	private String boardContent;
	
	
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
	
	@Override
	public String toString() {
		return "BoardRegisterDTO [userIdx=" + userIdx + ", boardTitle=" + boardTitle + ", boardFavoriteAnimal="
				+ boardFavoriteAnimal + ", boardContent=" + boardContent + ", getUserIdx()=" + getUserIdx()
				+ ", getBoardTitle()=" + getBoardTitle() + ", getBoardFavoriteAnimal()=" + getBoardFavoriteAnimal()
				+ ", getBoardContent()=" + getBoardContent() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
