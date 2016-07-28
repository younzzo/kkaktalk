package net.nigne.kkt.service;

import java.util.List;

import net.nigne.kkt.domain.TradeVO;

public interface TradeService {
	public TradeVO get(int no);
	public List<TradeVO> getList();
	public void insert(TradeVO vo);
	public void update(TradeVO vo);
	public void delete(int no);
}
