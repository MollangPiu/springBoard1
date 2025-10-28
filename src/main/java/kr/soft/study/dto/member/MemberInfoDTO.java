package kr.soft.study.dto.member;

public class MemberInfoDTO {

	private long idx;
	private String userId;
	private String ninkName;
	
	
	public long getIdx() {
		return idx;
	}
	public void setIdx(long idx) {
		this.idx = idx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNinkName() {
		return ninkName;
	}
	public void setNinkName(String ninkName) {
		this.ninkName = ninkName;
	}
}
