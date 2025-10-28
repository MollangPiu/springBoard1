package kr.soft.study.dto.member;

public class MemberInfoDTO {

	private long userIdx;
	private String userId;
	private String userNinkName;
	public long getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(long userIdx) {
		this.userIdx = userIdx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNinkName() {
		return userNinkName;
	}
	public void setUserNinkName(String userNinkName) {
		this.userNinkName = userNinkName;
	}
	
	@Override
	public String toString() {
		return "MemberInfoDTO [userId=" + userId + ", userIdx=" + userIdx + ", userNinkName=" + userNinkName + "]";
	}
}
