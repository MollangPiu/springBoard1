package kr.soft.study.dto.board;

public class BoardSearchDTO {

	private String searchAnimal;
	private String searchKeyword;

	public String getSearchAnimal() {
		return searchAnimal;
	}

	public void setSearchAnimal(String searchAnimal) {
		this.searchAnimal = searchAnimal;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	@Override
	public String toString() {
		return "BoardSearchDTO [searchAnimal=" + searchAnimal + ", searchKeyword=" + searchKeyword + "]";
	}
	
}
