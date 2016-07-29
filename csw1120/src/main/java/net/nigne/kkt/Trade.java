package net.nigne.kkt;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.zip.Inflater;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import net.nigne.kkt.domain.ProductImageVO;
import net.nigne.kkt.domain.TradeAllVO;
import net.nigne.kkt.domain.TradeVO;
import net.nigne.kkt.service.ProductImageService;
import net.nigne.kkt.service.TradeService;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "/trade")
public class Trade {
	

	@Resource(name = "uploadPath")
	private String uploadPath;
	private static final Logger logger = LoggerFactory.getLogger(Trade.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Inject
	private TradeService tradeservice;
	@Inject
	private ProductImageService productImageservice;
	// /유닛/ R 목록
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(Locale locale, Model model, TradeVO vo) {
		ModelAndView aaa = new ModelAndView();//데이터와 화면을 같이 처리할 수 있는 객체
		aaa.setViewName("list");
		List<TradeVO> list = tradeservice.getList();
		aaa.addObject("list", list);
		List<ProductImageVO> imagelist = productImageservice.getList();
		aaa.addObject("imagelist",imagelist);
		return aaa;	
	}
	
	//  /유닛/글번호	상세
	@RequestMapping(value = "/{no}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable("no") Integer no) {
		ModelAndView aaa = new ModelAndView();
		aaa.setViewName("detail");
		TradeVO vo = tradeservice.get(no);
		aaa.addObject("vo", vo);
		List<ProductImageVO> imagelist = productImageservice.getList();
		aaa.addObject("imagelist",imagelist);
		return aaa;
	}
	
	// /유닛/new  글쓰기 페이지
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView uploader() {
		return new ModelAndView("uploader");
	}

	//사진등록
	@Transactional
	@RequestMapping(value = "/write", method = RequestMethod.POST)
		public ModelAndView fileUpload(HttpServletRequest req, MultipartHttpServletRequest mhsq,TradeVO vo) throws IllegalStateException, IOException  {
		tradeservice.insert(vo);
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
	                 
	                String saveFileName = genId + "." + originalfileName;
	                // 저장되는 파일 이름
	 
	                String savePath = uploadPath + saveFileName; // 저장 될 파일 경로
	 
	               // int fileSize = mf.get(i).getSize(); // 파일 사이즈
	 
	                mf.get(i).transferTo(new File(savePath)); // 파일 저장
	 
	                productImageservice.uploadFile(originalfileName, saveFileName);
	            }
	        }
		return new ModelAndView("redirect:/trade");
	}
	
	@RequestMapping(value ="/delete", method= RequestMethod.POST)
	public ModelAndView delete(@RequestParam("no")int no, Model model) {
		tradeservice.delete(no);
		productImageservice.delete(no);
		return new ModelAndView("redirect:/trade");
	}

	// /유닛/   method=post
//	@RequestMapping(value = "", method = RequestMethod.POST)
//	public ModelAndView insert(TradeVO vo) {
//		
//		return new ModelAndView("uploader");
//	}
//	@Resource(name="uploadPath")
//	private String uploadPath;
//	private String uploadFile(String orgName, byte[] file) throws Exception {
//		UUID uid = UUID.randomUUID();
//		String fileName = uid.toString() + "_" + orgName;
//		File target = new File(uploadPath, fileName);
//		FileCopyUtils.copy(file, target);
//		return fileName;
//	}
	
}
