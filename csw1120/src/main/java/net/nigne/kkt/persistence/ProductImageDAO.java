package net.nigne.kkt.persistence;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import net.nigne.kkt.domain.ProductImageVO;

public interface ProductImageDAO {
	public ProductImageVO get(int no);
	public List<ProductImageVO> getList();
	//public void insert(ProductImageVO vo);
	public void update(ProductImageVO vo);
	public void delete(int trade_no);
	public void uploadFile(HashMap<String, Object> hm);
}
