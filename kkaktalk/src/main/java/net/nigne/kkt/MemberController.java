package net.nigne.kkt;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.nigne.kkt.domain.ChattingVO;
import net.nigne.kkt.domain.FriendsVO;
import net.nigne.kkt.domain.MemberVO;
import net.nigne.kkt.service.ChattingService;
import net.nigne.kkt.service.MemberService;

@Controller
@RestController
@RequestMapping("")
public class MemberController {
	
	@Inject
	private MemberService service;
	
	@Inject
	private ChattingService chatservice;
	
	//로그인페이지
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView login(HttpSession session) {
		
		ModelAndView view = new ModelAndView();
		
		String login_email = (String)session.getAttribute("login_email");
		String login_pw = (String)session.getAttribute("login_pw");

		System.out.println("11login_email:"+login_email);
		System.out.println("11login_pw:"+login_pw);
		
		if(login_email!=null || login_pw!=null){
			view.setViewName("redirect:/friend_list");
		}else{
			view.setViewName("login");
		}
		
		return view;
	}
	
	@RequestMapping(value = "/agree", method = RequestMethod.POST) 
	public ModelAndView agree(MemberVO vo, Model model, HttpSession session) {

		ModelAndView view = new ModelAndView();

		view.setViewName("agree");
		
		return view;
	}
	
	//회원가입페이지
	@RequestMapping(value = "/join", method = RequestMethod.GET) 
	public ModelAndView join() {

		ModelAndView view = new ModelAndView();
		view.setViewName("join");
		
		return view;
	}

	@Resource(name="uploadPath")
	private String uploadPath;
	
	//회원가입 db에 insert
	@RequestMapping(value = "", method = RequestMethod.POST) 
	public ModelAndView insert(MultipartFile file, MemberVO vo, Model model) throws Exception {
		
		ModelAndView view = new ModelAndView();

		List<MemberVO> memberinfo = service.info(vo.getEmail());

		//회원가입할때 이메일 중복확인
		if(memberinfo.isEmpty()){
			if(file.getSize()>0){ 
				String fileName = uploadFile(file.getOriginalFilename(), file.getBytes());
				
				vo.setThumbnail(fileName);

			}else{
				vo.setThumbnail("noImage.jpg");
			}
			
			model.addAttribute("message", "회원가입이 완료되었습니다.");
			model.addAttribute("returnUrl", "/");
			
			view.setViewName("alertAndRedirect");
			
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			vo.setPw(passwordEncoder.encode(vo.getPw()));
			
			service.insert(vo);
			
		}else{
			model.addAttribute("message", "이미 사용중인 이메일입니다.");
			model.addAttribute("returnUrl", "/join");
			
			view.setViewName("alertAndRedirect");
		}
		
		return view;
	}
	
	private String uploadFile(String orgName, byte[] file) throws Exception{
		
		UUID uid = UUID.randomUUID();
		String fileName = uid.toString() + "_" + orgName;
		File target = new File(uploadPath, fileName);
		FileCopyUtils.copy(file, target);
		
		return fileName;
	}
	
	@RequestMapping(value = "/friend_list", method = RequestMethod.GET)
	public ModelAndView loginGet(HttpSession session) {

		ModelAndView view = new ModelAndView();

		MemberVO vo = new MemberVO();
		
		String login_email = (String)session.getAttribute("login_email");
		
		//로그인상태면 친구목록 보여주기
		if(login_email!=null){
		
			vo.setEmail(login_email);

			List<MemberVO> memberinfo = service.info(vo.getEmail());
			view.addObject("memberinfo", memberinfo);
			
			view.setViewName("friend_list");
			
			List<MemberVO> friendlist = service.friendList(vo.getEmail());
			view.addObject("friendlist", friendlist);
			
		}else{ //로그인 상태가 아니면 로그인페이지로 이동
			view.setViewName("redirect:/");
		}

		return view;
	}
	
	//로그인페이지에서 로그인버튼 클릭시
	@RequestMapping(value = "/friend_list", method = RequestMethod.POST)
	public ModelAndView loginProcess(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response){

		ModelAndView view = new ModelAndView();

		MemberVO vo = new MemberVO();
		
		String email = request.getParameter("login_email");
		String pw = request.getParameter("login_pw");
		vo.setEmail(email);
		vo.setPw(pw);
		//vo.setThumbnail(thumbnail);
		
		session.setAttribute("login_email", vo.getEmail());
		session.setAttribute("login_pw", vo.getPw());
		
		String login = service.login(vo.getEmail());
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 

		if(passwordEncoder.matches(vo.getPw(), login)){//로그인 email, pw와 db에 있는 email, pw가 일치하면 친구목록페이지로 이동
			List<MemberVO> memberinfo = service.info(vo.getEmail());
			view.addObject("memberinfo", memberinfo);

			view.setViewName("friend_list");
			
			List<MemberVO> friendlist = service.friendList(vo.getEmail());
			view.addObject("friendlist", friendlist);
		}else{//패스워드가 일치하지 않으면 alert창 띄움
			session.invalidate();
			
			model.addAttribute("message", "로그인실패");
			model.addAttribute("returnUrl", "/");
			
			view.setViewName("alertAndRedirect");
			
		}
		return view;
	}
	
	//친구목록에서 친구검색
	@RequestMapping(value = "/friend_list/{search:.+}", method = RequestMethod.GET)
	public ModelAndView listsearch(@PathVariable("search") String search, Model model, MemberVO vo, HttpSession session){
		
		ModelAndView view = new ModelAndView();
		
		vo.setEmail((String)session.getAttribute("login_email"));
		
		String email = vo.getEmail();
		
		List<MemberVO> friendListSearch = service.friendListSearch(email, search);
		
		if(friendListSearch.isEmpty()){
			model.addAttribute("message", "일치하는 사용자가 없습니다.");
			model.addAttribute("returnUrl", "/friend_list");
			
			view.setViewName("alertAndRedirect");
		}else{
			List<MemberVO> memberinfo = service.info(vo.getEmail());
			view.addObject("memberinfo", memberinfo);
			
			view.addObject("friendlist", friendListSearch);
			view.setViewName("friend_list");
		}
		
		return view;
	}
	
	//로그아웃페이지
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutProcess() {

		ModelAndView view = new ModelAndView();
		view.setViewName("logout");
		
		return view;
	}
	
	//친구찾기페이지(자신을 등록한 친구들이 뜸. 단,서로친구인경우 안뜸)
	@RequestMapping(value = "/friend_search", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView friend_search(MemberVO vo, HttpSession session, HttpServletRequest request) {

		ModelAndView view = new ModelAndView();
		
		vo.setEmail((String)session.getAttribute("login_email"));
		
		List<MemberVO> friendSearch = service.friendSearch(vo.getEmail());
		
		view.addObject("friendSearch", friendSearch);
		
		view.setViewName("friend_search");
		
		return view;
	}
	
	//친구찾기페이지에서 친구검색
	@RequestMapping(value = "/friend_search/{search:.+}", method = RequestMethod.GET)
	public ModelAndView search(@PathVariable("search") String search, Model model){
		
		ModelAndView view = new ModelAndView();
		
		List<MemberVO> friendSearch = service.info(search);
		
		if(friendSearch.isEmpty()){
			model.addAttribute("message", "일치하는 사용자가 없습니다.");
			model.addAttribute("returnUrl", "/friend_search");
			
			view.setViewName("alertAndRedirect");
		}else{
			view.addObject("friendSearch", friendSearch);
			view.setViewName("friend_search");
		}
		
		return view;
	}
	
	//친구찾기페이지에서 친구추가 버튼을 눌렀을때
	@RequestMapping(value = "/friendAdd/{friend_email:.+}", method = RequestMethod.GET) 
	public ModelAndView friendAdd(FriendsVO vo, @PathVariable("friend_email") String friend_email, HttpSession session, Model model) {
		
		ModelAndView view = new ModelAndView();
		
		vo.setFriend_email(friend_email);
		vo.setMember_email((String)session.getAttribute("login_email"));

		if(vo.getFriend_email()==vo.getMember_email() || vo.getFriend_email().equals(vo.getMember_email())){
			model.addAttribute("message", "자신은 친구로 등록할 수 없습니다.");
			model.addAttribute("returnUrl", "/friend_search");
			
			view.setViewName("alertAndRedirect");
		}else{
			List<FriendsVO> alreadyFriend = service.alreadyFriend(vo.getMember_email(), vo.getFriend_email());
			
			if(alreadyFriend.isEmpty()){//친구로 등록이 안되있을경우 친구추가
				service.friendAdd(vo);
				view.setViewName("redirect:/friend_list");
			}else{//이미 친구로 등록이 되 있을 경우
				model.addAttribute("message", "이미 친구입니다.");
				model.addAttribute("returnUrl", "/friend_search");
				
				view.setViewName("alertAndRedirect");
			}
		}
		return view;
	}
	
	//프로필수정페이지
	@RequestMapping(value = "/memberUpdate", method = RequestMethod.GET)
	public ModelAndView memberUpdate(HttpSession session, MemberVO vo) {

		ModelAndView view = new ModelAndView();
		
		vo.setEmail((String)session.getAttribute("login_email"));
		List<MemberVO> memberinfo = service.info(vo.getEmail());
		view.addObject("memberinfo", memberinfo);
		
		view.setViewName("memberUpdate");
		
		return view;
	}
	
	@RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
	public ModelAndView memberUpdatePost(MultipartFile file, MemberVO vo) throws Exception {

		ModelAndView view = new ModelAndView();
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
		
		if(file.getSize()>0){ 
			String fileName = uploadFile(file.getOriginalFilename(), file.getBytes());
			
			vo.setThumbnail(fileName);

		}else{
			vo.setThumbnail("noImage.jpg");
		}
		
		System.out.println("프로필수정pw:"+vo.getPw());
		vo.setPw(passwordEncoder.encode(vo.getPw()));
		
		service.memberUpdate(vo);
		
		view.setViewName("redirect:/friend_list");
		
		return view;
	}
	
	//채팅방 리스트
	@RequestMapping(value = "/chat_list", method = RequestMethod.GET) 
	public ModelAndView chat_list(HttpSession session) {

		ModelAndView view = new ModelAndView();
		
		String login_email = (String)session.getAttribute("login_email");
		
		List<MemberVO> memberinfo = service.info(login_email);
		view.addObject("memberinfo", memberinfo);
		
		List<ChattingVO> chatList = chatservice.chattingList(login_email);
		
		view.addObject("chatList", chatList);
				
		view.setViewName("chat_list");
		
		return view;
	}
	
	//채팅
	@RequestMapping(value = "/chat/{friend_email:.+}", method = RequestMethod.GET) 
	public ModelAndView chat(ChattingVO vo, @PathVariable("friend_email") String friend_email, HttpSession session, HttpServletRequest request) {

		ModelAndView view = new ModelAndView();

		String login_email = (String)session.getAttribute("login_email");
		
		vo.setEmail(login_email);
		
		System.out.println("login_email:"+login_email);
		
		System.out.println("friend_email:"+friend_email);
		
		ChattingVO chattingCheck = chatservice.chattingCheck(login_email, friend_email);
		
		if(chattingCheck==null){
			System.out.println("채팅방생성");
			chatservice.chattingInsert();
			
			int chattingRoomNO = chatservice.chattingRoomNO();
			System.out.println("chattingRoomNO:"+chattingRoomNO);
			
			chatservice.userListInsert(chattingRoomNO, login_email);
			chatservice.userListInsert(chattingRoomNO, friend_email);
		}
		
		System.out.println("chattingCheck:"+chattingCheck);
		
		view.addObject("chattingCheck", chattingCheck);
		
		List<ChattingVO> msgList = chatservice.msgList(login_email, friend_email);
		view.addObject("msgList", msgList);
		
		List<MemberVO> memberinfo = service.info(login_email);
		view.addObject("memberinfo", memberinfo);
		
		view.addObject("friend_email", friend_email);
		
		//vo.setEmail(login_email);
		
		//view.addObject("member_name", login_email);
		//view.addObject("friend_name", friend_name);
		
		view.setViewName("chat");
		
		return view;
	}
	
	//MSG insert	
	@RequestMapping(value = "/msg_insert", method = RequestMethod.POST) 
	public ModelAndView msgInsert(ChattingVO vo, Model model, HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView();
		
		System.out.println("vo.getChatting_room_no():"+vo.getChatting_room_no());
		
		System.out.println("vo.getEmail():"+vo.getEmail());
		
		System.out.println("vo.getMsg():"+vo.getMsg());
		
		chatservice.msgInsert(vo);

		String friend_email = request.getParameter("friend_email");
		
		view.setViewName("redirect:/chat/"+friend_email);
		
		return view;
	}
}
