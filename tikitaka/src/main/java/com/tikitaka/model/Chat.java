package com.tikitaka.model;

public class Chat {

	private Long no;
	private String title;
	private String contents;
	private String createTime;
	private int joinCount;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getJoinCount() {
		return joinCount;
	}
	public void setJoinCount(int joinCount) {
		this.joinCount = joinCount;
	}
	@Override
	public String toString() {
		return "Chat [no=" + no + ", title=" + title + ", contents=" + contents + ", createTime=" + createTime
				+ ", joinCount=" + joinCount + "]";
	}
	
	
}
