package com.jiudianlianxian.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiudianlianxian.entity.User;
import com.jiudianlianxian.test.MyData;

/**
 * Servlet implementation class MainFrameServlet
 * <p>Title: MainFrameServlet</p>
 * <p>Description: 登录成功后的主界面</p>
 * <p>Company: </p>
 * @author fupengpeng
 * @date 2017年7月15日 上午11:17:54
 *
 */
public class MainFrameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainFrameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//Servlet之间传递数据方式1：使用静态类
//		out.println("<h1>主界面</h1>"+"用戶名："+MyData.name);
		
		//Servlet之间传递数据方式2：sendRedirect（）方法拼接参数
		//獲取登錄名
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		out.println("<h1>主界面</h1>"+"用戶名："+username+"   密碼："+password);
		//Servlet之间传递数据方式3：使用session来传递
		String usernameSession = (String) request.getSession().getAttribute("username");
		out.println("<h1>主界面</h1>"+"用戶名session："+usernameSession);
		//获取session中的对象
		User user = (User) request.getSession().getAttribute("userobj");
		out.println("<h1>主界面</h1>"+"用戶名--User："+user.getUsername()+"   密码--User："+user.getPassword());
		
		out.println("<a href='/UsersManager/LoginServlet'>返回登录界面重新登录</a>");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
