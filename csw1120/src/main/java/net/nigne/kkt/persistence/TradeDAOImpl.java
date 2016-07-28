package net.nigne.kkt.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.nigne.kkt.domain.TradeVO;

@Repository
public class TradeDAOImpl implements TradeDAO{
	
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace ="net.nigne.kkt.mappers.tradeMapper";
	@Override
	public TradeVO get(int no) {
		return sqlSession.selectOne(namespace + ".get", no); 
	}

	@Override
	public List<TradeVO> getList() {
		return sqlSession.selectList(namespace + ".getList"); 
	}

	@Override
	public void insert(TradeVO vo) {
		sqlSession.insert(namespace+ ".insert", vo);
		
	}

	@Override
	public void update(TradeVO vo) {
		sqlSession.update(namespace + ".update", vo);
		
	}

	@Override
	public void delete(int no) {
		sqlSession.delete(namespace + ".delete", no);
		
	}

	@Override
	public void increaseReply(int no) {
		sqlSession.update(namespace + ".increaseReply", no);
		
	}

}
