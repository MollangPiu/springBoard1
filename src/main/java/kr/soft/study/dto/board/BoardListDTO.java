package kr.soft.study.dto.board;

public class BoardListDTO {

	private long boardIdx;
	private String boardTitle;
	private String boardCreatedAt;
	private String boardFavoriteAnimal;
	private int boardViewCount;
	private String boardNickName;
	private String animalKor;
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
	
	
}
