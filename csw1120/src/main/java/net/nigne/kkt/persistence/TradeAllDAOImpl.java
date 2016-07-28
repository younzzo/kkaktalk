package net.nigne.kkt.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import net.nigne.kkt.domain.TradeAllVO;
import net.nigne.kkt.domain.TradeVO;

public class TradeAllDAOImpl implements TradeAllDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace ="net.nigne.kkt.mappers.tradeMapper";
	@Override
	public List<TradeAllVO> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
