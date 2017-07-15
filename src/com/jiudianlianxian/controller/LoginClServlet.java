package com.jiudianlianxian.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiudianlianxian.entity.User;
import com.jiudianlianxian.test.MyData;

/**
 * Servlet implementation class LoginClServlet
 * <p>Title: LoginClServlet</p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author fupengpeng
 * @date 2017年7月15日 上午11:01:14
 *
 */
public class LoginClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginClServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//接收用户提交的用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("用户名："+username + "   密码："+ password);
		
		
		
		//数据库匹配数据，判断是否存在用户名和密码
		if(
//				"fupengpeng".endsWith(username) &&
				
				"123456".equals(password)){
			//账号密码匹配上，跳转至登录成功界面（servlet提供了两种方法，Sendredirct--转向    forward--转发）
			// sendRedirect("url");  url:/应用名/servlet的url
			//Servlet之间传递数据方式1：使用静态类
//			MyData.name = username;
			//Servlet之间传递数据方式2：sendRedirect（）方法拼接参数，不可以传递对象
//			response.sendRedirect("/UsersManager/MainFrameServlet?username="+username+"&password="+password);
			//Servlet之间传递数据方式3：使用session来传递,可以传递对象
			
			request.getSession().setAttribute("username", username);
		
			User user = new User();
			user.setUsername("hahah");
			user.setPassword("222222");
			request.getSession().setAttribute("userobj", user);
			
			response.sendRedirect("/UsersManager/MainFrameServlet");
			
		}else{
			//账号密码错误，弹出提示并返回登录界面
			response.sendRedirect("/UsersManager/LoginServlet");
			
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
