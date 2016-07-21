package com.study.springrest.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.study.springrest.domain.ProductImageVO;
import com.study.springrest.persistance.ProductImageDAO;

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

	@Transactional
	@Override
	public void update(ProductImageVO vo) {
		dao.update(vo);
		
	}

	@Transactional
	@Override
	public void delete(int no) {
		System.out.println("ProductImageService�� delete()�� ���ӵ�"+no);
		dao.delete(no);
		
	}
	
	@Transactional
	@Override
	public void delete_image(int image_no) {
		System.out.println("ProductImageService�� delete_image()�� ���ӵ�"+image_no);
		dao.delete_image(image_no);
		
	}

	@Transactional
	@Override
	public void insert(ProductImageVO vo) {
		dao.insert(vo);
		
	}
	
	@Transactional
	@Override
	public void uploadFile(String originalfileName, String saveFileName) {
	    HashMap<String, Object> hm = new HashMap<>();
	    hm.put("originalfileName", originalfileName);
	    hm.put("saveFileName", saveFileName);
	    dao.uploadFile(hm);
	}
	
	@Transactional
	@Override
	public void update_uploadFile(int trade_no, String originalfileName, String saveFileName) {
	    HashMap<String, Object> hm = new HashMap<>();
	    hm.put("originalfileName", originalfileName);
	    hm.put("saveFileName", saveFileName);
	    hm.put("trade_no", trade_no);
	    System.out.println("ProductImageService�� update_uploadFile()�� ���ӵ�"+originalfileName+"�����������ϳ����̰�"+saveFileName+"����Ǵ������̸��̰�");
	    dao.update_uploadFile(hm);
	}

}
