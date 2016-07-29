package net.nigne.kkt.service;
import java.util.List;
import java.util.Map;

import net.nigne.kkt.domain.EscrowVO;

public interface EscrowService {
	
	public int getNo();
	public EscrowVO get(int no);
	public List<EscrowVO> getList();
	public List<EscrowVO> getSearchList(Map<String, String> map);
	public void insertEscrow(EscrowVO vo);
	public void deleteEscrow(int no);
	public void updateEscrow(EscrowVO vo);
	
}
