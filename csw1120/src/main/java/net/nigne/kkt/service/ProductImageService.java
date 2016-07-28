package net.nigne.kkt.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import net.nigne.kkt.domain.ProductImageVO;

public interface ProductImageService {
	public ProductImageVO get(int no);
	public void update(ProductImageVO vo);
	public void delete(int trade_no);
	//public void insert(ProductImageVO vo);
	public void uploadFile(String originalfileName, String saveFileName);
	public List<ProductImageVO> getList();
}
