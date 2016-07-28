package net.nigne.kkt.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.nigne.kkt.domain.ProductImageVO;
import net.nigne.kkt.domain.TradeVO;
import net.nigne.kkt.persistence.ProductImageDAO;
import net.nigne.kkt.persistence.TradeDAO;

@Service
public class TradeServiceImpl implements TradeService {
	@Inject
	private TradeDAO dao;
	@Override
	public TradeVO get(int no) {
		// TODO Auto-generated method stub
		return dao.get(no);
	}

	@Override
	public List<TradeVO> getList() {
		return dao.getList(); 
	}

	@Override
	public void insert(TradeVO vo) {
		dao.insert(vo);
		
	}

	@Override
	public void update(TradeVO vo) {
		dao.update(vo);
		
	}

	@Override
	public void delete(int no) {
		dao.delete(no);
		
	}
	
}
