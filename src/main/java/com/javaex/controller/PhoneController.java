package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {
	
	//필드
	@Autowired
	private PhoneDao phoneDao;
	//생성자
	//메소드(게터세터)
	//메소드(일반)
	
	//리스트
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST} )
	public String list(Model model) {
		System.out.println("[PhoneController.list]");
		
		//Dao 사용
		//PhoneDao phoneDao = new PhoneDao(); //위에 필드에 올려주고 Autowired를 써줘서 없어도 쓸수있다.
		
		
		//Dao의 메소드로 데이터 가져오기
		List<PersonVo> personList = phoneDao.getPersonList();  
		
		
		//그림에서 4번 ModelAndView 에서 Model 공간에 담기  --> 디스패처서블릿에 전달된다 --> request의 attribute 영역에 넣는다. 
		model.addAttribute("personList", personList);
		
		//ModelAndView 에서 View 공간
		return "/WEB-INF/views/list.jsp";   // DispatcherServlet야 WEB-INF/views/test.jsp에 포워드해라!!
	}
	
	
	
	//쓰기폼
	@RequestMapping(value = "/writeForm", method = {RequestMethod.GET, RequestMethod.POST} )
	public String writeForm() {
		System.out.println("[PhoneController.writeForm]");
		
		return "/WEB-INF/views/writeForm.jsp";
	}
	
	//http://localhost:8088/phonebook5/write?name=정우성&hp=010-0000-0000&company=02-1234-1234
	
	
	
	
	// 쓰기
//	@ModelAttribute 일떄!!!
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("[PhoneController.write]");

		//@ModelAttribute  --> new PersonVo()
//						   --> 기본생성자 + setter로 넣는다  꼭 Vo에 기본생성자 있어야함
		
		System.out.println(personVo);
		
		//Dao 사용
		//PhoneDao phoneDao = new PhoneDao();
		
		//Dao 메소드 이용해서 데이터 저장
		int count = phoneDao.personInsert(personVo);

		//view --> 리다이렉트  Model사용안했음
		return "redirect:/list";
	}
	
	
	
	//삭제
	@RequestMapping(value="/delete", method = {RequestMethod.GET , RequestMethod.POST}) 
	public String delete(@RequestParam("personId") int personId) {
		System.out.println("[PhoneController.delete]");
		
		//잘 넘어오는지 확인
		System.out.println(personId);  
		
		//Dao 열기
		//PhoneDao phoneDao = new PhoneDao();
		
		//Dao 메소드 이용
		//int count = phoneDao.personDelete(personId);
		
		//Model은 없고 View ==> 리다이렉트
		return "redirect:/list";
	}
	
	
	//수정폼
	@RequestMapping(value="/updateForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String updateForm(Model model, @RequestParam("personId") int personId) {
		System.out.println("[PhoneController.updateForm]");
		
		// Dao 사용
		//PhoneDao phoneDao = new PhoneDao();

		// Dao의 메소드로 수정할 1명의 데이터 가져오기
		//PersonVo personVo = phoneDao.getPerson(personId);
		
		//확인
		System.out.println(personId);

		// 그림에서 4번 ModelAndView 에서 Model 공간에 담기 --> 디스패처서블릿에 전달된다 --> request의 attribute
		// 영역에 넣는다.
		//model.addAttribute("personList", personVo);

		// ModelAndView 에서 View 공간
		return "/WEB-INF/views/updateForm.jsp"; // DispatcherServlet야 WEB-INF/views/test.jsp에 포워드해라!!
		
	}
	
	
	//수정
	@RequestMapping(value="update", method = {RequestMethod.GET, RequestMethod.POST})
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("[PhoneController.update]");
		
		//위에 @ModelAttribute PersonVo personVo  --> new PersonVo()
//		   --> 기본생성자 + setter로 넣는다  꼭 Vo에 기본생성자 있어야함

		System.out.println(personVo);
		
		// Dao 사용
		//PhoneDao phoneDao = new PhoneDao();
		
		// Dao 메소드 이용해서 데이터 업데이트
		//int count = phoneDao.personUpdate(personVo);

		// view --> 리다이렉트 Model사용안했음
		return "redirect:/list";
		
	
	}
	
	
//	  파라미터를 1개씩 받을때
//	쓰기
//	@RequestMapping(value = "/write", method = {RequestMethod.GET, RequestMethod.POST} )
//	public String write(@RequestParam("name") String name, @RequestParam("hp") String hp, @RequestParam("company") String company) {
//		 System.out.println("[PhoneController.write]");
//		System.out.println(name);
//		System.out.println(hp);
//		System.out.println(company);
//		
//		
//		PersonVo personVo = new PersonVo(name, hp, company);
//		System.out.println(personVo);
//		
//		return "/WEB-INF/vies/write.jsp";
//	}

	
	
//	//쓰기
//	// 파라미터가 있을때도 있고 없을때도 있을때
//	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
//	public String write(@RequestParam("name") String name, @RequestParam("hp") String hp,
//			@RequestParam(value = "company", required = false, defaultValue = "-1") String company) {
//		System.out.println("[PhoneController.write]");
//		System.out.println(name);
//		System.out.println(hp);
//		System.out.println(company);
//
//		PersonVo personVo = new PersonVo(name, hp, company);
//		System.out.println(personVo);
//
//		return "/WEB-INF/vies/write.jsp";
//	}
	
	
	
		
	/********************************************************************************************/
	//PathVariable
	@RequestMapping(value = "/board/read/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(@PathVariable("no") int boardNo) {
		System.out.println("read");
		
		//localhost:8088/phonebook5/board/read/1       read다음에 /숫자들이 위에 {no} ,no ,boardNo에 담긴다.
		//localhost:8088/phonebook5/board/read/3
		//localhost:8088/phonebook5/board/read/100
		
		
		System.out.println(boardNo);
		
		return "";
	}
	
	
	
	
	
	
	@RequestMapping(value="/test")		//이단계 꼭 테스트 해볼것!!
	public String test() {
		System.out.println("test");
		
		return "WEB-INF/views/test.jsp";  // DispatcherServlet야 WEB-INF/views/test.jsp에 포워드해라!!

	}
	
}


