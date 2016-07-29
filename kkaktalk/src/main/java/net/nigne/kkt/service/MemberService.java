package net.nigne.kkt.service;

import java.util.List;

import net.nigne.kkt.domain.FriendsVO;
import net.nigne.kkt.domain.MemberVO;

public interface MemberService {

	public List<MemberVO> getList();
	public List<MemberVO> info(String email);
	public void insert(MemberVO vo);
	public String login(String email);
	public List<MemberVO> friendList(String email);
	public List<MemberVO> friendListSearch(String email, String search);
	public List<MemberVO> friendSearch(String email);
	public void friendAdd(FriendsVO vo);
	public List<FriendsVO> alreadyFriend(String member_email, String friend_email);
	public void memberUpdate(MemberVO vo);
	
}
