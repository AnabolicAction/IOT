package com.hw.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.hw.frame.Biz;
import com.hw.vo.Correlation;

@Controller
public class MainController {

	Connection conn;

	public MainController() {
		try {
			Class.forName("org.apache.hive.jdbc.HiveDriver");
			conn = DriverManager.getConnection("jdbc:hive2://192.168.111.100:10000/default", "root", "111111");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/main.do")
	public String main() {
		return "main";
	}

	@RequestMapping("/chart1.do")
	public String chart1(Model m) {
		m.addAttribute("center", "chart1");
		return "main";
	}

	@RequestMapping("/chart2.do")
	public String chart2(Model m) {
		m.addAttribute("center", "chart2");
		return "main";
	}

	@RequestMapping("/chart3.do")
	public String chart3(Model m) {
		m.addAttribute("center", "chart3");
		return "main";
	}

	@RequestMapping("/chart4.do")
	public String chart4(Model m) {
		m.addAttribute("center", "chart4");
		return "main";
	}
	
	@RequestMapping("/hzmr.do")
	public String hzmr(Model m) {
		m.addAttribute("center", "hzmr");
		return "main";
	}
	
	

	// Ajax가 요청되면 ok라는 문자 뿌림
	@RequestMapping("/chart1impl.do")
	@ResponseBody // output으로 값 쏘겠다
	public void chart1impl(HttpServletResponse res) throws Exception {

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt
				.executeQuery("select Year, Month, count(*) " + "from airline_delay " + "where delayYear = 2006 "
						+ "and Month in (1,2,3,4) " + "and ArrDelay > 0 " + "group by Year, Month ");

		JSONArray ja = new JSONArray();
		while (rs.next()) {
			JSONArray data = new JSONArray();
			// [] 이게 하나 만들어짐
			data.add(rs.getInt(2) + "월");
			data.add(rs.getInt(3));
			ja.add(data); // 배열 안의 배열 모양이 된다. chart1의 data와 일치
		}

		res.setCharacterEncoding("EUC-KR");
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();

		out.print(ja.toJSONString());
		out.close();
	}

	// Ajax가 요청되면 ok라는 문자 뿌림
	@RequestMapping("/chart2impl.do")
	@ResponseBody // output으로 값 쏘겠다
	public void chart2impl(HttpServletResponse res) throws Exception {

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt
				.executeQuery("select Year, Month, count(*) " + "from airline_delay " + "where delayYear = 2006 "
						+ "and Month in (1,2,3,4) " + "and ArrDelay > 0 " + "group by Year, Month ");

		JSONArray ja = new JSONArray();
		while (rs.next()) {
			JSONObject data = new JSONObject();
			// [] 이게 하나 만들어짐
			data.put("name", rs.getInt(2) + "월");
			data.put("y", rs.getInt(3));
			ja.add(data); // 배열 안의 배열 모양이 된다. chart1의 data와 일치
		}

		res.setCharacterEncoding("EUC-KR");
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();

		out.print(ja.toJSONString());
		out.close();
	}

	// Ajax가 요청되면 ok라는 문자 뿌림
	@RequestMapping("/chart3impl.do")
	@ResponseBody // output으로 값 쏘겠다
	public void chart3impl(HttpServletResponse res) throws Exception {

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select C.LocalName, sum(A.Cnt) " + "from RealInfo_Cold_SiGunGu A "
				+ "join SiGunGu_LocalCode B on (A.SiGunGuLocalCode = B.SiGunGuLocalCode) "
				+ "join SiDo_LocalCode C on (B.HighSiDoLocalCode  = C.SiDoLocalCode) " + "group by C.LocalName ");

		JSONArray ja = new JSONArray();
		while (rs.next()) {
			JSONObject data = new JSONObject();
			// [] 이게 하나 만들어짐
			data.put("name", rs.getString(1));
			data.put("y", rs.getInt(2));
			ja.add(data); // 배열 안의 배열 모양이 된다. chart1의 data와 일치
		}

		res.setCharacterEncoding("EUC-KR");
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();

		out.print(ja.toJSONString());
		out.close();
	}

	@Resource(name = "correlationBiz")
	Biz<Correlation, String> biz1;

	// Ajax가 요청되면 ok라는 문자 뿌림
	@RequestMapping("/chart4impl.do")
	@ResponseBody // output으로 값 쏘겠다
	public void chart4impl(HttpServletResponse res) throws Exception {

		List<Correlation> list = null;
		list = biz1.get();
		JSONArray obj_arr = new JSONArray();

		for (int i = 0; i < list.size(); i++) {
			obj_arr.add(list.get(i).getMain());
		}
		// obj.put("name", "만족도");
		// obj.put("data", obj_arr);
		// ja.add(obj);

		res.setCharacterEncoding("EUC-KR");
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();
		out.print(obj_arr.toJSONString());
		out.close();
	}

	@Resource(name = "correlationBiz")
	Biz<Correlation, String> biz2;

	// Ajax가 요청되면 ok라는 문자 뿌림
	@RequestMapping("/chart4impl2.do")
	@ResponseBody // output으로 값 쏘겠다
	public void chart4impl2(HttpServletResponse res) throws Exception {

		List<Correlation> list = null;
		list = biz2.get();
		JSONArray arr = new JSONArray();
		JSONObject obj_arr = new JSONObject();
		JSONArray inner_arr = new JSONArray();

		obj_arr.put("name", "만족도");
		for (int i = 0; i < list.size(); i++) {
			inner_arr.add(list.get(i).getScore());
		}
		obj_arr.put("data", inner_arr);

		JSONObject obj_arr2 = new JSONObject();

		JSONArray inner_arr2 = new JSONArray();
		obj_arr2.put("name", "녹지갯수");
		for (int i = 0; i < list.size(); i++) {
			inner_arr2.add(list.get(i).getTotal_area() / 1000);
		}
		obj_arr2.put("data", inner_arr2);

		arr.add(obj_arr);
		arr.add(obj_arr2);

		/*
		 * [{ name: '만족도', data: [1,2] }, { name: '녹지갯수', data: [1,2] }]
		 */
		res.setCharacterEncoding("EUC-KR");
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();
		out.print(arr.toJSONString());
		out.close();
	}


	@Resource(name = "correlationBiz")
	Biz<Correlation, String> biz4;

	// Ajax가 요청되면 ok라는 문자 뿌림
	@RequestMapping("/chart1impl.do")
	@ResponseBody // output으로 값 쏘겠다
	public void chart5impl(HttpServletResponse res) throws Exception {

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt
				.executeQuery("select Year, Month, count(*) " + "from airline_delay " + "where delayYear = 2006 "
						+ "and Month in (1,2,3,4) " + "and ArrDelay > 0 " + "group by Year, Month ");

		JSONArray ja = new JSONArray();
		while (rs.next()) {
			JSONArray data = new JSONArray();
			// [] 이게 하나 만들어짐
			data.add(rs.getInt(2) + "월");
			data.add(rs.getInt(3));
			ja.add(data); // 배열 안의 배열 모양이 된다. chart1의 data와 일치
		}

		res.setCharacterEncoding("EUC-KR");
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();

		out.print(ja.toJSONString());
		out.close();
	}
}
