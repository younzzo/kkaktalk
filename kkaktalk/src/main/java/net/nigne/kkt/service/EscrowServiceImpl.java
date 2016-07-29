package net.nigne.kkt.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nigne.kkt.domain.ProductImageVO;
import net.nigne.kkt.domain.EscrowVO;
import net.nigne.kkt.persistence.ProductImageDAO;
import net.nigne.kkt.persistence.EscrowDAO;

@Service
public class EscrowServiceImpl implements EscrowService{
	
	@Inject
	private EscrowDAO dao;
	
	@Inject
	private ProductImageDAO imagedao;

	
	@Override
	public int getNo() {
		return dao.getNo();
	}

	@Override
	public EscrowVO get(int no) {
		return dao.get(no);
	}
	

	@Override
	public List<EscrowVO> getSearchList(Map<String, String> map) {
		System.out.println("TradeService狼 getSearchList()肺 立加凳");
		System.out.println(map);
		return dao.getSearchList(map);
	}
	

	@Override
	public List<EscrowVO> getList() {
		return dao.getList();
	}
	
	@Transactional
	@Override
	public void insertEscrow(EscrowVO vo) {

		
	}

	@Override
	public void deleteEscrow(int no) {
		System.out.println("TradeService狼 deleteProduct()肺 立加凳"+no);
		dao.deleteEscrow(no);
		
	}

	@Override
	public void updateEscrow(EscrowVO vo) {
		dao.updateEscrow(vo);
		
	}



}
