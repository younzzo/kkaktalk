package net.nigne.kkt.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.nigne.kkt.domain.ChattingVO;
import net.nigne.kkt.persistence.ChattingDAO;

@Service
public class ChattingServiceImpl implements ChattingService {

	@Inject
	private ChattingDAO dao;
	
	@Override
	public List<ChattingVO> chattingList(String email) {
		return dao.chattingList(email);
	}

	@Override
	public ChattingVO chattingCheck(String email, String friend_email) {
		return dao.chattingCheck(email, friend_email);
	}

	@Override
	public void chattingInsert() {
		dao.chattingInsert();
	}

	@Override
	public void userListInsert(int chatting_room_no, String email) {
		dao.userListInsert(chatting_room_no, email);
	}

	@Override
	public int chattingRoomNO() {
		return dao.chattingRoomNO();
	}

	@Override
	public void msgInsert(ChattingVO vo) {
		dao.msgInsert(vo);
	}

	@Override
	public List<ChattingVO> msgList(String email, String friend_email) {
		return dao.msgList(email, friend_email);
	}


}
