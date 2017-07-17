package com.jiudianlianxian.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyInfoForm
 */
public class MyInfoForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h1>用户调查问卷</h1>");
		//action说明：/web应用名/Servlet的url
		out.println("<form action='/UsersManager/RegisterCLServlet' method='post'>");
		out.println("用户名<input type='text' name='username'/><br/>");
		out.println("密　码<input type='password' name='password'/><br/>");
		out.println("密　码<input type='radio' name='sex' value='男'/>男<input type='radio' name='sex' value='女'/>女<br/>");
		out.println("爱好<input type='checkbox' name='hobby' value='音乐'/>音乐 <input type='checkbox' name='hobby' value='哈哈'/>哈哈 <input type='checkbox' name='hobby' value='体育'/>体育<br/>");
		out.println("所在城市：<select name='city'><option value='beijing'>北京</option><option value='chongqing'>重庆</option></select><br/>");
		out.println("个人介绍：<textarea cols='20' rows='10' name='intro'></textarea><br/>");
		out.println("提交照片：<input type='file' name='photo'/><br/>");
		//hidden使用：1.不希望用户看到的数据。2.不影响界面，同时业务逻辑要使用此数据
		out.println("<input type='hidden' value='abc' name='hidde01'/><br/>");
		out.println("<input type='submit' value='提交信息'/><br/>");
		out.println("</form>");

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
