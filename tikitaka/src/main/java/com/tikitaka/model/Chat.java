package com.tikitaka.model;

public class Chat {

	private Long no;
	private String title;
	private String contents;
	private int join_count;
	private String create_time;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getJoin_count() {
		return join_count;
	}
	public void setJoin_count(int join_count) {
		this.join_count = join_count;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	@Override
	public String toString() {
		return "Chat [no=" + no + ", title=" + title + ", contents=" + contents + ", join_count=" + join_count
				+ ", create_time=" + create_time + "]";
	}
	

	
}
