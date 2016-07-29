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
	// /����/ R ���
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(Locale locale, Model model, TradeVO vo) {
		ModelAndView aaa = new ModelAndView();//�����Ϳ� ȭ���� ���� ó���� �� �ִ� ��ü
		aaa.setViewName("list");
		List<TradeVO> list = tradeservice.getList();
		aaa.addObject("list", list);
		List<ProductImageVO> imagelist = productImageservice.getList();
		aaa.addObject("imagelist",imagelist);
		return aaa;	
	}
	
	//  /����/�۹�ȣ	��
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
	
	// /����/new  �۾��� ������
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView uploader() {
		return new ModelAndView("uploader");
	}

	//�������
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
	                // ���� �ߺ��� ó��
	                String genId = UUID.randomUUID().toString(); 
	                // ���� ���ϸ�
	                String originalfileName = mf.get(i).getOriginalFilename(); 
	                 
	                String saveFileName = genId + "." + originalfileName;
	                // ����Ǵ� ���� �̸�
	 
	                String savePath = uploadPath + saveFileName; // ���� �� ���� ���
	 
	               // int fileSize = mf.get(i).getSize(); // ���� ������
	 
	                mf.get(i).transferTo(new File(savePath)); // ���� ����
	 
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

	// /����/   method=post
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
