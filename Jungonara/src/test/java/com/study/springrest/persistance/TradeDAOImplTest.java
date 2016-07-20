package com.study.springrest.persistance;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.springrest.domain.TradeVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class TradeDAOImplTest {
	
	@Inject
	private TradeDAO dao;
	
	@Test
	public void test(){
		System.out.println("Test DAO= "+dao);
	}
	
	
	@Test
	public void insertTest() throws Exception{
		TradeVO vo = new TradeVO();
	
		vo.setCategory("��Ÿ");
		vo.setContent("����4");
		vo.setPrice(2000);
		vo.setTitle("Ÿ��Ʋ4");
		vo.setMember_email("csw4@hanmail.net");
		System.out.println("1�߰� ����? ");
		dao.insertProduct(vo);
		System.out.println("2�߰� ����? ");
	}
		
//	@Test
//	public void getTest() throws Exception{
//		int no=6;
//		TradeVO vo= dao.get(no);
//		System.out.println("��� �̸��� :"+vo.getMember_email());
//	}
	
	
	@Test
	public void deleteTest() throws Exception{
		 dao.deleteProduct(16);
	}
	
	@Test
	public void updateTest() throws Exception{
		TradeVO vo = new TradeVO();
		vo.setTitle("���ο� Ÿ��Ʋ");
		vo.setContent("���ο��");
		vo.setMember_email("ljh@daum.net");
		dao.updateProduct(vo);
	}
		
	@Test
	public void getListTest() throws Exception{
		List<TradeVO> trade_list= dao.getList();
		Iterator<TradeVO> it=trade_list.iterator();
		while(it.hasNext()){
			TradeVO vo= it.next();
			System.out.println("�̸� :"+vo.getTitle());	
		}
		
	}

}
