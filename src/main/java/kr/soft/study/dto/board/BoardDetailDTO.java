package kr.soft.study.dto.board;

public class BoardDetailDTO {

	private long boardIdx;
	private String boardTitle;
	private String boardContent;
	private String boardCreatedAt;
	private String boardFavoriteAnimal;
	private int boardViewCount;
	private String boardNickName;
	private String animalKor;
	private long userIdx;
	
	
	public long getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(long boardIdx) {
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
	public String getBoardCreatedAt() {
		return boardCreatedAt;
	}
	public void setBoardCreatedAt(String boardCreatedAt) {
		this.boardCreatedAt = boardCreatedAt;
	}
	public String getBoardFavoriteAnimal() {
		return boardFavoriteAnimal;
	}
	public void setBoardFavoriteAnimal(String boardFavoriteAnimal) {
		this.boardFavoriteAnimal = boardFavoriteAnimal;
	}
	public int getBoardViewCount() {
		return boardViewCount;
	}
	public void setBoardViewCount(int boardViewCount) {
		this.boardViewCount = boardViewCount;
	}
	public String getBoardNickName() {
		return boardNickName;
	}
	public void setBoardNickName(String boardNickName) {
		this.boardNickName = boardNickName;
	}
	public String getAnimalKor() {
		return animalKor;
	}
	public void setAnimalKor(String animalKor) {
		this.animalKor = animalKor;
	}
	public long getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(long userIdx) {
		this.userIdx = userIdx;
	}
}
