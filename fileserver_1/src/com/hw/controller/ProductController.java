package com.hw.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.hw.frame.Biz;
import com.hw.vo.Product;

@Controller
public class ProductController {

	@Resource(name="productBiz")
	Biz<Product,Integer> biz;
	
	@RequestMapping("/productlist.do")
	public String list(Model m) {
		// Database에서 데이터를 가지고 온다.
		List<Product> list = null;
		list = biz.get();
		System.out.println(list.get(0).getName());
		// ------------------------
		m.addAttribute("productlist", list);
		m.addAttribute("center", "product/list");
		return "main";
	}
	@RequestMapping("/productadd.do")
	public String add(Model m) {
		m.addAttribute("center", "product/add");
		return "main";
	}
	@RequestMapping("/productaddimpl.do")
	public String addimpl(Model m,Product p) {
		System.out.println(p);
		MultipartFile mf = p.getMf();
		String imgName = mf.getOriginalFilename();
		System.out.println(imgName);
		
		
		//2018.03.12 추가함
		p.setImgname(imgName);
		biz.register(p);
		
		// C:\\spring\\mv\\web\\img
		
		byte[] data = null;
		FileOutputStream fo = null;
		try {
			data = mf.getBytes();
			fo = 
			new FileOutputStream("C:\\spring\\mv\\web\\img\\"+imgName);
			fo.write(data);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
//		// DB에 입력
//		m.addAttribute("imgname",imgName);
//		m.addAttribute("center", "product/addok");
		List<Product> list = null;
		list = biz.get();
		System.out.println(list.get(0).getName());
		// ------------------------
		m.addAttribute("productlist", list);
		m.addAttribute("center", "product/list");
		return "main";
	}
	@RequestMapping("/productupdate.do")
	public String listupdate(Model m, Integer id) {
		// Database에서 데이터를 가지고 온다.
		Product product = null;
		product = biz.get(id);
		m.addAttribute("productupdate", product);
		m.addAttribute("center", "product/update");
		return "main";
	}
	@RequestMapping("/productmodity.do")
	public String listmodity(Model m, Product p) {
		System.out.println(p);
		MultipartFile mf = p.getMf();
		String imgName = mf.getOriginalFilename();
		System.out.println(imgName);
		System.out.println("imgname 널임 "+imgName+"/" +imgName.length());
		if(imgName.length() == 0 || mf == null) {
			imgName = p.getImgname();
			System.out.println("imgname 널임 "+imgName);
		}
		
		//2018.03.12 추가함
		p.setImgname(imgName);
		System.out.println(p.getId());
		System.out.println(p.getName());
		System.out.println(p.getPrice());
		System.out.println(p.getImgname());
		biz.modify(p);

		
		File file = new File("C:\\spring\\mv\\web\\img\\"+imgName);
		if(file.exists()) {
			file.delete();
		}

		byte[] data = null;
		FileOutputStream fo = null;
		try {
			data = mf.getBytes();
			fo = 
			new FileOutputStream("C:\\spring\\mv\\web\\img\\"+imgName);
			fo.write(data);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

//		
//		// DB에 입력
//		m.addAttribute("imgname",imgName);
//		m.addAttribute("center", "product/addok");
		// Database에서 데이터를 가지고 온다.
		List<Product> list = null;
		list = biz.get();
		System.out.println(list.get(0).getName());
				// ------------------------
		m.addAttribute("productlist", list);
		m.addAttribute("center", "product/list");
		return "main";
	}
	

	@RequestMapping("/productdelete.do")
	public String listdelete(Model m, int id) {
		// Database에서 데이터를 가지고 온다.
		biz.remove(id);
		
		System.err.println("id " + id + " 삭제 성공");
		List<Product> list = null;
		list = biz.get();
		m.addAttribute("productlist", list);
		m.addAttribute("center", "product/list");
		return "main";
	}
}




