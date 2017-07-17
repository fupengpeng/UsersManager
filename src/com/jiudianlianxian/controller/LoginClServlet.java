package com.jiudianlianxian.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiudianlianxian.entity.User;
import com.jiudianlianxian.test.MyData;

/**
 * Servlet implementation class LoginClServlet
 * <p>
 * Title: LoginClServlet
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// 接收用户提交的用户名和密码
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		// System.out.println("用户名："+username + "   密码："+ password);

		// 学习测试
//		test(request, response, username, password);

		// 数据库中查询数据进行匹配验证登录
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String result = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 2.得到连接
			connection =
			// jdbc:oracle:this:@127.0.0.1:1521:ORCLHSP
			DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/shoppingmall", "root", "root");

			// 连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码
			// 3.创建Statement
			statement = connection.createStatement();
//			statement = connection.prepareStatement("select * from user where username=? and password=?");
			//4.查询数据库获取数据
			resultSet = statement.executeQuery("select * from user");
			//5.便利数据匹配数据，判断是否登录成功
			while (resultSet.next()) {
				System.out.println(resultSet.getString("account") + "===="
						+ resultSet.getString("password"));
				if (resultSet.getString("account").equals(account)
						&& resultSet.getString("password").equals(password)) {

					request.getRequestDispatcher("/MainFrameServlet").forward(request, response);
					System.out.println("账号密码匹配，登录成功");
					result = "Login Success!";
				} else {
//					request.getRequestDispatcher("/LoginServlet").forward(request, response);
					System.out.println("账号密码不匹配，登录失败");
					result = "Sorry! Account or password error.";
				}

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void test(HttpServletRequest request, HttpServletResponse response,
			String username, String password) throws ServletException,
			IOException {
		String url = request.getRequestURL().toString(); // 请求的网络地址
		System.out.println("请求网络地址URL：" + url);
		String uri = request.getRequestURI(); // 请求的uri
		System.out.println("uri:" + uri);
		String queryString = request.getQueryString(); // 请求的字段
		System.out.println("queryString:" + queryString);

		// 获取请求方的主机名
		String host = request.getRemoteHost();
		System.out.println("主机名：" + host);

		// 获取请求方的网络端口号
		int port = request.getRemotePort();
		System.out.println("请求方的网络端口号port:" + port);
		// 获取请求方web服务器所使用的网络端口号
		String localPort = request.getRemoteHost();
		System.out.println("请求方web服务器所使用的网络端口号localPort：" + host);
		// 获取请求方web服务器ip地址
		String localAddr = request.getLocalAddr();
		System.out.println("请求方web服务器ip地址localAddr：" + localAddr);
		// 获取请求方web服务器主机名
		String localName = request.getLocalAddr();
		System.out.println("请求方web服务器主机名localName：" + localName);

		if (request.getHeader("x-forwarded-for") == null) {

			// String remoteAddrString = request.getRemoteAddr();
			System.out.println("remoteAddrString==" + request.getRemoteAddr());
		} else {
			System.out.println("未获取到ip" + request.getHeader("x-forwarded-for"));

		}

		// //实现禁止当前用户的登录
		// String remoteAddrString = request.getRemoteAddr();
		// if(remoteAddrString.equals("192.168.1.40")){ //如果访问ip是（192.168.1.40）就
		// 禁止访问
		//
		// System.out.println("remoteAddrString=="+remoteAddrString);
		// response.sendRedirect("/UsersManager/Error");
		//
		//
		// }

		// 数据库匹配数据，判断是否存在用户名和密码
		if (
		// "fupengpeng".endsWith(username) &&

		"111111".equals(password)) {
			// 账号密码匹配上，跳转至登录成功界面（servlet提供了两种方法，Sendredirct--转向 forward--转发）
			// sendRedirect("url"); url:/应用名/servlet的url
			// Servlet之间传递数据方式1：使用静态类
			// MyData.name = username;
			// Servlet之间传递数据方式2：sendRedirect（）方法拼接参数，不可以传递对象
			// response.sendRedirect("/UsersManager/MainFrameServlet?username="+username+"&password="+password);
			// Servlet之间传递数据方式3：使用session来传递,可以传递对象

			// request.getSession().setAttribute("username", username);
			//
			// User user = new User();
			// user.setUsername("hahah");
			// user.setPassword("222222");
			// request.getSession().setAttribute("userobj", user);
			//
			// response.sendRedirect("/UsersManager/MainFrameServlet");

			// Servlet之间传递数据方式4：转发方式，使用request的setAttribute方法
			// 把username放入到域对象
			request.setAttribute("username", username);
			// 表示使用转发的方法把request和response对象传递给下一个servlet
			request.getRequestDispatcher("/Servlet02").forward(request,
					response);
			; // 分派器

		} else {
			// 账号密码错误，弹出提示并返回登录界面
			response.sendRedirect("/UsersManager/LoginServlet");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
