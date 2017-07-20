package com.jiudianlianxian.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiudianlianxian.domain.User;

/**
 * Servlet implementation class UpdateUserView
 */
//@WebServlet("/UpdateUserView")
public class UpdateUserView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//获取从上个界面传递过来的user
		User user = (User) request.getAttribute("user");
		out.println("<h1>修改用户信息</h1>");
		out.println("<table border=1px bordercolor=green cellspacing=0 width=300px>");
		out.println("<tr><td>id</td><td>"+user.getId()+"</td></tr>");
		out.println("<tr><td>uid</td><td>"+user.getUid()+"</td></tr>");
		out.println("<tr><td>username</td><td>"+user.getUsername()+"</td></tr>");
		out.println("<tr><td>sex</td><td>"+user.getSex()+"</td></tr>");
		out.println("<tr><td>phonenumber</td><td>"+user.getPhonenumber()+"</td></tr>");
		out.println("<tr><td>location</td><td>"+user.getLocation()+"</td></tr>");
		out.println("<tr><td>detailedaddress</td><td>"+user.getDetailedaddress()+"</td></tr>");
		out.println("<tr><td>postcode</td><td>"+user.getPostcode()+"</td></tr>");
		out.println("<tr><td>birthday</td><td>"+user.getBirthday()+"</td></tr>");
		out.println("<tr><td>wechat</td><td>"+user.getWechat()+"</td></tr>");
		out.println("<tr><td>growthvalue</td><td>"+user.getGrowthvalue()+"</td></tr>");
		out.println("<tr><td>account</td><td>"+user.getAccount()+"</td></tr>");
		out.println("<tr><td>integral</td><td>"+user.getIntegral()+"</td></tr>");
		out.println("<tr><td>isdefaultaddress</td><td>"+user.getIsdefaultaddress()+"</td></tr>");
		out.println("</table>");
		out.println("");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
