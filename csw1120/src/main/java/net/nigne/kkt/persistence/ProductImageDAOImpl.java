package net.nigne.kkt.persistence;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import net.nigne.kkt.domain.ProductImageVO;

@Repository
public class ProductImageDAOImpl implements ProductImageDAO{
	
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace ="net.nigne.kkt.mappers.productImageMapper";
	@Override
	public ProductImageVO get(int no) {
		return sqlSession.selectOne(namespace + ".get", no); 
	}

	@Override
	public List<ProductImageVO> getList() {
		return sqlSession.selectList(namespace + ".getList"); 
	}

//	@Override
//	public void insert(Product_imageVO vo) {
//		sqlSession.insert(namespace+ ".insert", vo);
//		
//	}

	@Override
	public void update(ProductImageVO vo) {
		sqlSession.update(namespace + ".update", vo);
		
	}

	@Override
	public void delete(int trade_no) {
		sqlSession.delete(namespace + ".delete", trade_no);
		
	}

//	@Override
//	public void insert(ProductImageVO vo) {
//		sqlSession.insert(namespace+ ".insert", vo);
//		
//	}
	public void uploadFile(HashMap<String, Object> hm){
		sqlSession.insert(namespace+".uploadFile", hm);
	}



}
