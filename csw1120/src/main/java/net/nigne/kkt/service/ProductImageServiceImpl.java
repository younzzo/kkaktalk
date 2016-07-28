package net.nigne.kkt.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.nigne.kkt.domain.ProductImageVO;
import net.nigne.kkt.persistence.ProductImageDAO;

@Service
public class ProductImageServiceImpl implements ProductImageService {
	@Inject
	private ProductImageDAO dao;
	
	@Override
	public ProductImageVO get(int no) {
		return dao.get(no);
	}

	@Override
	public List<ProductImageVO> getList() {
		return dao.getList();
	}

	@Override
	public void update(ProductImageVO vo) {
		dao.update(vo);
		
	}

	@Override
	public void delete(int trade_no) {
		dao.delete(trade_no);
		
	}

//	@Override
//	public void insert(ProductImageVO vo) {
//		dao.insert(vo);
//		
//	}
	@Override
	public void uploadFile(String originalfileName, String saveFileName) {
	    HashMap<String, Object> hm = new HashMap<>();
	    hm.put("originalfileName", originalfileName);
	    hm.put("saveFileName", saveFileName);
	    dao.uploadFile(hm);
	}



}
