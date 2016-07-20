package com.study.springrest.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.NodeList;

import com.study.springrest.controller.Trade;
import com.study.springrest.domain.ProductImageVO;
import com.study.springrest.domain.TradeVO;
import com.study.springrest.service.TradeService;

import com.study.springrest.service.ProductImageService;

@RestController
@RequestMapping("/trade")
public class Trade {
	

	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@Inject
	private TradeService service;
	
	@Inject
	private ProductImageService productImageservice;

	//	/유닛/ 목록
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(Locale locale, Model model) {
		ModelAndView aaa = new ModelAndView();
		aaa.setViewName("trade_list");
		
		List<TradeVO> trade_list = service.getList();
		aaa.addObject("trade_list",trade_list);
		
		List<ProductImageVO> uploadFileList = productImageservice.getList();
		aaa.addObject("uploadFileList",uploadFileList);
			
		return aaa;
	}
	
	
	//	/유닛/글번호	상세
	@RequestMapping(value = "/{no}", method = RequestMethod.GET)
	public ModelAndView List(@PathVariable("no") Integer no) {
		
		ModelAndView aaa = new ModelAndView();
		aaa.setViewName("detail");
		
		List<ProductImageVO> vo2 = productImageservice.getList();
		aaa.addObject("vo2", vo2);
		
		TradeVO vo = service.get(no);
		aaa.addObject("vo", vo);
		
	
		return aaa;
	}
	
	
	//	/유닛/글번호 수정 -> update 페이지 호출
	@RequestMapping(value = "/{no}/updateList", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable("no") Integer no) {
		ModelAndView aaa = new ModelAndView();
		aaa.setViewName("update");
		
		TradeVO vo = service.get(no);
		aaa.addObject("vo", vo);
		
		List<ProductImageVO> vo2 = productImageservice.getList();
		aaa.addObject("vo2", vo2);
		return aaa;
	}
	
/*	
	//수정
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(TradeVO vo, Model model){
		service.updateProduct(vo);
		return new ModelAndView("redirect:/trade");
	}
	*/
	
	//사진수정
			@Transactional
			@RequestMapping(value = "/update", method = RequestMethod.POST)
				public ModelAndView update_fileUpload(HttpServletRequest req, MultipartHttpServletRequest mhsq, TradeVO vo) throws IllegalStateException, IOException  {
			
				service.updateProduct(vo);
				
				List<MultipartFile> mf = mhsq.getFiles("file");
				System.out.println("mf="+mf);
				 File dir = new File(uploadPath);
			        if (!dir.isDirectory()) {
			            dir.mkdirs();
			        }
				 if (mf.size() == 1 && mf.get(0).getOriginalFilename().equals("")) {
			        } else {
			            for (int i = 0; i < mf.size(); i++) {
			                // 파일 중복명 처리
			                String genId = UUID.randomUUID().toString(); 
			                // 본래 파일명
			                String originalfileName = mf.get(i).getOriginalFilename(); 
			                System.out.println("11111"+originalfileName);
			                
			                String saveFileName = genId + "." + originalfileName;
			                // 저장되는 파일 이름
			                System.out.println("2222222"+saveFileName);
			                
			                String savePath = uploadPath + saveFileName; // 저장 될 파일 경로
			                System.out.println("333333"+savePath);
			                
			               // int fileSize = mf.get(i).getSize(); // 파일 사이즈
			                mf.get(i).transferTo(new File(savePath)); // 파일 저장
			                
			                productImageservice.update_uploadFile(vo.getNo(),originalfileName, saveFileName);
			            }
			        }
				return new ModelAndView("redirect:/trade");
			}
	


	//검색결과
	@RequestMapping(value = "/searchList", method = RequestMethod.GET)
	public ModelAndView searchList(@RequestParam(required=false) String keyfield, @RequestParam(required=false) String keyword){
		ModelAndView bbb = new ModelAndView();
		bbb.setViewName("trade_list");
	
		System.out.println("keyfield="+keyfield+"이고, keyword="+keyword);
		Map<String, String> map = new HashMap<String, String> ();
        map.put("keyfield" , keyfield);
        map.put("keyword", keyword);
        List<TradeVO> search_list = service.getSearchList(map);
      	bbb.addObject("trade_list", search_list);
          
        List<ProductImageVO> uploadFileList = productImageservice.getList();
  		bbb.addObject("uploadFileList",uploadFileList);
	
		return bbb;
	}
	

	
	//	/유닛/new 글쓰기 페이지
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView write() {
		ModelAndView aaa = new ModelAndView();
		aaa.setViewName("write");
		return aaa;
	}
	
	
	
	//	/유닛/new 글쓰기 페이지
//	@RequestMapping(value = "", method = RequestMethod.POST)
//	public ModelAndView insert(TradeVO vo) {
//		service.insertProduct(vo);
//		return new ModelAndView("redirect:/trade");
//	}
	
	
	//사진등록
		@Transactional
		@RequestMapping(value = "", method = RequestMethod.POST)
			public ModelAndView fileUpload(HttpServletRequest req, MultipartHttpServletRequest mhsq, TradeVO vo) throws IllegalStateException, IOException  {
		
			service.insertProduct(vo);
			
			List<MultipartFile> mf = mhsq.getFiles("file");
			 File dir = new File(uploadPath);
		        if (!dir.isDirectory()) {
		            dir.mkdirs();
		        }
			 if (mf.size() == 1 && mf.get(0).getOriginalFilename().equals("")) {
		        } else {
		            for (int i = 0; i < mf.size(); i++) {
		                // 파일 중복명 처리
		                String genId = UUID.randomUUID().toString(); 
		                // 본래 파일명
		                String originalfileName = mf.get(i).getOriginalFilename(); 
		                System.out.println("11111"+originalfileName);
		                
		                String saveFileName = genId + "." + originalfileName;
		                // 저장되는 파일 이름
		                System.out.println("2222222"+saveFileName);
		                
		                String savePath = uploadPath + saveFileName; // 저장 될 파일 경로
		                System.out.println("333333"+savePath);
		                
		               // int fileSize = mf.get(i).getSize(); // 파일 사이즈
		                mf.get(i).transferTo(new File(savePath)); // 파일 저장
		                productImageservice.uploadFile(originalfileName, saveFileName);
		            }
		        }
			return new ModelAndView("redirect:/trade");
		}
	
	
	
	//	/유닛/번호 삭제
	@RequestMapping(value = "/{no}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable("no") Integer no) {
		System.out.println("no=?"+no);
		//productImageservice.delete(no);	 //외래키의 속성으로 이걸 먼저 지워줘야 삭제가 됨!!!
		service.deleteProduct(no);
		return new ModelAndView("redirect:/trade");
		
	}

	//	/유닛/사진 삭제
	@RequestMapping(value = "/{no}/{image_no}", method = RequestMethod.POST)
	public ModelAndView delete_image( @PathVariable("no") Integer trade_no,@PathVariable("image_no") Integer image_no) {
		System.out.println("no=?"+image_no);
		//productImageservice.delete(no);	 //외래키의 속성으로 이걸 먼저 지워줘야 삭제가 됨!!!
		productImageservice.delete_image(image_no);
		
		return new ModelAndView("redirect:/trade/{no}/updateList");
		
	}

	


}
