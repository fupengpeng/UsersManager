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
 * 
 * Title: AddUserView
 * Description: 添加用户
 * Company: 济宁九点连线信息技术有限公司
 * ProjectName: UsersManager
 * @author fupengpeng
 * @date 2017年7月21日 上午10:13:24
 *
 */
//@WebServlet("/AddUserView")
public class AddUserView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserView() {
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
		out.println("<h1>添加用户</h1>");
		out.println("<form action='/UsersManager/UserClServlet?type=add' method='post'>");
		System.out.println("提交添加用户请求，挑战至UserClServlet");
		out.println("<table border=1px bordercolor=green cellspacing=0 width=300px>");
		out.println("<tr><td>id</td><td><input type='text' name='id' /></td></tr>");
		out.println("<tr><td>uid</td><td><input type='text' name='uid'/></td></tr>");
		out.println("<tr><td>username</td><td><input type='text' name='username' /></td></tr>");
		out.println("<tr><td>sex</td><td><input type='text' name='sex' /></td></tr>");
		out.println("<tr><td>phonenumber</td><td><input type='text' name='phonenumber'/></td></tr>");
		out.println("<tr><td>location</td><td><input type='text' name='location'/></td></tr>");
		out.println("<tr><td>detailedaddress</td><td><input type='text' name='detailedaddress' /></td></tr>");
		out.println("<tr><td>postcode</td><td><input type='text' name='postcode' /></td></tr>");
		out.println("<tr><td>birthday</td><td><input type='text' name='birthday'/></td></tr>");
		out.println("<tr><td>wechat</td><td><input type='text' name='wechat' /></td></tr>");
		out.println("<tr><td>growthvalue</td><td><input type='text' name='growthvalue' /></td></tr>");
		out.println("<tr><td>account</td><td><input type='text' name='account' /></td></tr>");
		out.println("<tr><td>account</td><td><input type='text' name='password' /></td></tr>");
		out.println("<tr><td>integral</td><td><input type='text' name='integral' /></td></tr>");
		out.println("<tr><td>isdefaultaddress</td><td><input type='text' name='isdefaultaddress' /></td></tr>");
		out.println("<tr><td><input type='submit' value='添加用户'/></td><td><input type='reset' name='uid' value='确认添加'/></td></tr>");
		out.println("</table><br/>");
		out.println("</form>");
		out.println("<input type='submit' name='uid' value='确认添加'/>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
