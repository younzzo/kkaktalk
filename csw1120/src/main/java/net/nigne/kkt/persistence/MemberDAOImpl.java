package net.nigne.kkt.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.nigne.kkt.domain.FriendsVO;
import net.nigne.kkt.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "net.nigne.kkt.mappers.memberMapper";
	
	@Override
	public List<MemberVO> getList() {
		return sqlSession.selectList(namespace + ".memberList");
	}

	@Override
	public void insert(MemberVO vo) {
		sqlSession.insert(namespace + ".memberInsert", vo);
	}

	@Override
	public MemberVO login(String email) {
		return sqlSession.selectOne(namespace+".memberLogin", email);
	}

	@Override
	public List<MemberVO> friendList(String email) {
		return sqlSession.selectList(namespace + ".friendList", email);
	}

	@Override
	public List<MemberVO> info(String email) {
		return sqlSession.selectList(namespace + ".memberInfo", email);
	}

	@Override
	public List<MemberVO> friendSearch(String email) {
		return sqlSession.selectList(namespace + ".friendSearch", email);
	}

	@Override
	public void friendAdd(FriendsVO vo) {
		sqlSession.insert(namespace + ".friendAdd", vo);
		
	}

	@Override
	public List<MemberVO> friendListSearch(String email, String search) {

		Map<String, Object> map = new HashMap<>();
		
		map.put("email", email);
		map.put("search", search);
		
		return sqlSession.selectList(namespace + ".friendListSearch", map);
	}

	@Override
	public List<FriendsVO> alreadyFriend(String member_email, String friend_email) {
		Map<String, Object> map = new HashMap<>();
	
		map.put("member_email", member_email);
		map.put("friend_email", friend_email);
		
		return sqlSession.selectList(namespace + ".alreadyFriend", map);
	}

	@Override
	public void memberUpdate(MemberVO vo) {
		sqlSession.update(namespace + ".memberUpdate", vo);
	}

}
