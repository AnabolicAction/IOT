package com.hw.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hw.frame.Biz;
import com.hw.vo.User;

@Controller
public class UserController {
		
	@Resource(name="userBiz")
	Biz<User,String> biz;
	
	@RequestMapping("/userlist.do")
	public String list(Model m) {
		// Database에서 데이터를 가지고 온다.
		List<User> list = null;
		list = biz.get();
		m.addAttribute("userlist", list);
		m.addAttribute("center", "user/list");
		return "main";
	}
	@RequestMapping("/useradd.do")
	public String add(Model m, User u) {
		m.addAttribute("center", "user/add");
		return "main";
	}
	@RequestMapping("/useraddimpl.do")
	public String addimpl(Model m, User u) {
		System.out.println("addimpl:"+m);
		// DB에 입력 한다.

		biz.register(u);
		m.addAttribute("center", "user/addok");
		return "main";
	}
	@RequestMapping("/userlistone.do")
	public String listone(Model m, String id) {
		// Database에서 데이터를 가지고 온다.
		User user = null;
		user = biz.get(id);
		m.addAttribute("userlistone", user);
		m.addAttribute("center", "user/listone");
		return "main";
	}
	@RequestMapping("/usermodity.do")
	public String listmodity(Model m, User u) {
		// Database에서 데이터를 가지고 온다.
		
		biz.modify(u);
		System.err.println("id " + u.getId() + " 수정 성공");
		List<User> list = null;
		list = biz.get();
		m.addAttribute("userlist", list);
		m.addAttribute("center", "user/list");
		return "main";
	}
	@RequestMapping("/userdelete.do")
	public String listdelete(Model m, String id) {
		// Database에서 데이터를 가지고 온다.
		biz.remove(id);
		System.err.println("id " + id + " 삭제 성공");
		List<User> list = null;
		list = biz.get();
		m.addAttribute("userlist", list);
		m.addAttribute("center", "user/list");
		return "main";
	}
	
}



