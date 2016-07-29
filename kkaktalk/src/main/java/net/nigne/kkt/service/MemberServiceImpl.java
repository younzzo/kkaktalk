package net.nigne.kkt.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.nigne.kkt.domain.FriendsVO;
import net.nigne.kkt.domain.MemberVO;
import net.nigne.kkt.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	
	@Override
	public List<MemberVO> getList() {
		return dao.getList();
	}

	@Override
	public void insert(MemberVO vo) {
		dao.insert(vo);
	}

	@Override
	public String login(String email) {
		return dao.login(email);
	}

	@Override
	public List<MemberVO> friendList(String email) {
		return dao.friendList(email);
	}

	@Override
	public List<MemberVO> info(String email) {
		return dao.info(email);
	}

	@Override
	public List<MemberVO> friendSearch(String email) {
		return dao.friendSearch(email);
	}

	@Override
	public void friendAdd(FriendsVO vo) {
		dao.friendAdd(vo);
	}

	@Override
	public List<MemberVO> friendListSearch(String email, String search) {
		return dao.friendListSearch(email, search);
	}

	@Override
	public List<FriendsVO> alreadyFriend(String member_email, String friend_email) {
		return dao.alreadyFriend(member_email, friend_email);
	}

	@Override
	public void memberUpdate(MemberVO vo) {
		dao.memberUpdate(vo);
	}

}
