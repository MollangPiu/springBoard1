package kr.soft.study.dto.board;

public class BoardSearchDTO {

	private String searchAnimal;
	private String searchKeyword;
	private int pageNum;	//현재 게시판 번호
	private int contentViewSize; //현재화면, 게시글 수
	
	public BoardSearchDTO() {
		this.contentViewSize = 10;
	}
	
	

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

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

	public int getContentViewSize() {
		return contentViewSize;
	}



	public void setContentViewSize(int contentViewSize) {
		this.contentViewSize = contentViewSize;
	}



	@Override
	public String toString() {
		return "BoardSearchDTO [searchAnimal=" + searchAnimal + ", searchKeyword=" + searchKeyword + ", pageNum="
				+ pageNum + ", contentViewSize=" + contentViewSize + "]";
	}

	
}
