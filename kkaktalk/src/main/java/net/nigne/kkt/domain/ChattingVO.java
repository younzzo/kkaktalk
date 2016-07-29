package net.nigne.kkt.domain;

public class ChattingVO {
	
	private String email;
	private String user_email;
	private String name;
	private String thumbnail;
	private String msg;
	private int chatting_room_no;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getChatting_room_no() {
		return chatting_room_no;
	}
	public void setChatting_room_no(int chatting_room_no) {
		this.chatting_room_no = chatting_room_no;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}	
	
}
