package net.nigne.kkt.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import net.nigne.kkt.domain.EscrowVO;
import net.nigne.kkt.domain.TradeVO;

@Repository
public class EscrowDAOImpl implements EscrowDAO{
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace= "net.nigne.kkt.mappers.escrowMapper";
	@Override
	public int getNo() {
		return sqlSession.selectOne(namespace+".getNo");
	}
	@Override
	public EscrowVO get(int no) {
		return sqlSession.selectOne(namespace+".get", no);
	}
	@Override
	public List<EscrowVO> getList() {
		return sqlSession.selectList(namespace+".getList");
	
	}
	@Override
	public List<EscrowVO> getSearchList(Map<String, String> map) {
		return sqlSession.selectList(namespace+".getSearchList", map);
	}
	@Override
	public void insertEscrow(EscrowVO vo) {
		sqlSession.insert(namespace+".insertProduct", vo);		
	}
	
	@Override
	public void deleteEscrow(int no) {
		sqlSession.delete(namespace+".deleteProduct", no);		
	}
	
	@Override
	public void updateEscrow(EscrowVO vo) {
		sqlSession.update(namespace+".updateProduct", vo);
		
	}


}
