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
 * Title: UpdateUserView
 * Description: 修改数据库数据
 * Company: 济宁九点连线信息技术有限公司
 * ProjectName: UsersManager
 * @author fupengpeng
 * @date 2017年7月21日 上午10:17:59
 *
 */
//@WebServlet("/UpdateUserView")
public class UpdateUserView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserView() {
        super();
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
		out.println("<form action='/UsersManager/UserClServlet?type=update' method='post'>");
		out.println("<table border=1px bordercolor=green cellspacing=0 width=300px>");
		out.println("<tr><td>id</td><td><input type='text' name='id' readonly value='"+user.getId()+"'/></td></tr>");
		out.println("<tr><td>uid</td><td><input type='text' name='uid' readonly value='"+user.getUid()+"'/></td></tr>");
		out.println("<tr><td>username</td><td><input type='text' name='username' value='"+user.getUsername()+"'/></td></tr>");
		out.println("<tr><td>sex</td><td><input type='text' name='sex' value='"+user.getSex()+"'/></td></tr>");
		out.println("<tr><td>phonenumber</td><td><input type='text' name='phonenumber' value='"+user.getPhonenumber()+"'/></td></tr>");
		out.println("<tr><td>location</td><td><input type='text' name='location' value='"+user.getLocation()+"'/></td></tr>");
		out.println("<tr><td>detailedaddress</td><td><input type='text' name='detailedaddress' value='"+user.getDetailedaddress()+"'/></td></tr>");
		out.println("<tr><td>postcode</td><td><input type='text' name='postcode' value='"+user.getPostcode()+"'/></td></tr>");
		out.println("<tr><td>birthday</td><td><input type='text' name='birthday' value='"+user.getBirthday()+"'/></td></tr>");
		out.println("<tr><td>wechat</td><td><input type='text' name='wechat' value='"+user.getWechat()+"'/></td></tr>");
		out.println("<tr><td>growthvalue</td><td><input type='text' name='growthvalue' value='"+user.getGrowthvalue()+"'/></td></tr>");
		out.println("<tr><td>account</td><td><input type='text' name='account' value='"+user.getAccount()+"'/></td></tr>");
		out.println("<tr><td>password</td><td><input type='text' name='password' value='"+user.getPassword()+"'/></td></tr>");
		out.println("<tr><td>integral</td><td><input type='text' name='integral' value='"+user.getIntegral()+"'/></td></tr>");
		out.println("<tr><td>isdefaultaddress</td><td><input type='text' name='isdefaultaddress' value='"+user.getIsdefaultaddress()+"'/></td></tr>");
		out.println("<tr><td><input type='submit' value='确认修改'/></td><td><input type='reset' name='uid' value='重新填写'/></td></tr>");
		out.println("</table><br/>");
		out.println("</form>");
		out.println("<input type='submit' name='uid' value='确认修改'/>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
