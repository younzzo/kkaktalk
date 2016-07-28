package net.nigne.kkt.persistence;

import java.util.List;

import net.nigne.kkt.domain.TradeAllVO;
import net.nigne.kkt.domain.TradeVO;

public interface TradeAllDAO {
	public List<TradeAllVO> getList();
}
