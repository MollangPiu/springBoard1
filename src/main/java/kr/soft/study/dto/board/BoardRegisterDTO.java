package kr.soft.study.dto.board;

public class BoardRegisterDTO extends BoardDTO{
	
	@Override
	public String toString() {
		return "BoardRegisterDTO [userIdx=" + userIdx + ", boardTitle=" + boardTitle + ", boardFavoriteAnimal="
				+ boardFavoriteAnimal + ", boardContent=" + boardContent + ", getUserIdx()=" + getUserIdx()
				+ ", getBoardTitle()=" + getBoardTitle() + ", getBoardFavoriteAnimal()=" + getBoardFavoriteAnimal()
				+ ", getBoardContent()=" + getBoardContent() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
