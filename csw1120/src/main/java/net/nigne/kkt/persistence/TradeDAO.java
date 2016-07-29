package net.nigne.kkt.persistence;

import java.util.List;

import net.nigne.kkt.domain.TradeVO;

public interface TradeDAO {
	public TradeVO get(int no);
	public List<TradeVO> getList();
	public void insert(TradeVO vo);
	public void update(TradeVO vo);
	public void delete(int no);
	public void increaseReply(int no);
}
