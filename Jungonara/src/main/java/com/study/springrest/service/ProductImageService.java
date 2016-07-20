package com.study.springrest.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.study.springrest.domain.ProductImageVO;

public interface ProductImageService {
	public ProductImageVO get(int no);
	public List<ProductImageVO> getList();
	public void update(ProductImageVO vo);
	public void delete(int no);
	public void delete_image(int image_no);
	public void insert(ProductImageVO vo);
	public void uploadFile(String originalfileName, String saveFileName);
	public void update_uploadFile(int trade_no,String originalfileName, String saveFileName);

	
}
