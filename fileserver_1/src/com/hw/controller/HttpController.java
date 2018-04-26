package com.hw.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;
import javax.print.attribute.standard.MediaSize.Other;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hw.frame.Biz;
import com.hw.vo.Correlation;
import com.hw.vo.Product;

@Controller
public class HttpController {

	@RequestMapping("/taewoo.do")
	public void addimpl(@RequestParam("file0") MultipartFile img) {
		
		MultipartFile mf = img;
		String imgName = mf.getOriginalFilename();
		System.out.println(imgName);
		
		
		
		// C:\\spring\\mv\\web\\img
		
		byte[] data = null;
		FileOutputStream fo = null;
		try {
			data = mf.getBytes();
			fo = 
			new FileOutputStream("C:\\network\\mv\\web\\img\\"+imgName);
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
		
	}
	
}
