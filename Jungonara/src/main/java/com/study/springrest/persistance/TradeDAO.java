package com.study.springrest.persistance;

import java.util.List;
import java.util.Map;

import com.study.springrest.domain.TradeVO;

public interface TradeDAO {
	
	public int getNo();
	public TradeVO get(int no);
	public List<TradeVO> getList();
	public List<TradeVO> getSearchList(Map<String, String> map);
	public void insertProduct(TradeVO vo);
	public void deleteProduct(int no);
	public void updateProduct(TradeVO vo);
	

}



