package com.jiudianlianxian.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiudianlianxian.domain.User;
import com.jiudianlianxian.service.UsersService;

/**
 * Servlet implementation class ManageUsers
 * Title: ManageUsers
 * Description: 用户管理servlet
 * Company: 济宁九点连线信息技术有限公司
 * ProjectName: UsersManager
 * @author fupengpeng
 * @date 2017年7月18日 上午9:52:28
 *
 */
public class ManageUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageUsers() {
		super();
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

		
		

		// 定义分页变量
		int pageNow = 1; // 当前页
		// 接收用户点击的pageNow
		String sPageNow = request.getParameter("pageNow");
		if (sPageNow != null) {
			pageNow = Integer.parseInt(sPageNow);
		}

		int pageSize = 3; // 指定每页显示3条记录
		int pageCount = 1; // 根据请款计算出来

		UsersService usersService = new UsersService();
		ArrayList<User> al = usersService.getUsersByPage(pageNow, pageSize);
		pageCount = usersService.getPageCount(pageSize);

		// 5.便利数据匹配数据，判断是否登录成功
		out.println("<script type='text/javascript' language='javascript'> ");
		out.println("function gotoPageNow(){ "
				+ "var pageNow = document.getElementById('pageNow'); "
				// + "window.alert('pageNow='+pageNow.value)"
				+ "window.open('/UsersManager/ManageUsers?pageNow='+pageNow.value,'_self');"
				+ "} ");
		out.println("</script> ");
		out.println("<h2>管理用户</h2><a href='/UsersManager/MainFrameServlet'>返回主界面</a><br/><br/><a href='/UsersManager/MainFrameServlet'>安全退出</a><br/>");
		out.println("<table border='1' bordercolor='green' width='500px'><br/>");
		out.println("<tr><th>uid</th><th>用户名</th><th>phonenumber</th><th>lication</th><th>detailedaddress</th><th>刪除用戶</th><th>修改用戶</th></tr><br/>");

		for (User user : al) {
			out.println("<tr><td>" + user.getUid() + "</td>"+ "<td>" 
		            + user.getUsername() + "</td>" + "<td>"
					+ user.getPhonenumber() + "</td>" + "<td>"
					+ user.getLocation() + "</td>" + "<td>"
					+ user.getDetailedaddress() + "</td>"
							+ "<td><a href='/UsersManager/DelClServlet?id="+user.getUid()+"'>刪除用戶</a></td>"
							+ "<td><a href='/UsersManager/ManageUsers'>修改用戶</a></td>  </tr><br/>");
		}
		out.println("</table><br/>");

		// 显示分页数据
		// 上一页
		if (pageNow != 1) {
			out.println("<a href='/UsersManager/ManageUsers?pageNow="
					+ (pageNow - 1) + "'>上一页</a>");
		}

		// 分页
		for (int i = 1; i <= pageCount; i++) {
			System.out.println(pageCount + "--" + i);
			out.println("<a href='/UsersManager/ManageUsers?pageNow=" + i
					+ "'><" + i + "></a>");
		}
		// 下一页
		if (pageNow != pageCount) {
			out.println("<a href='/UsersManager/ManageUsers?pageNow="
					+ (pageNow + 1) + "'>下一页</a>");
		}
		// 显示分页信息
		out.println("<br/>&nbsp;&nbsp;&nbsp;当前页" + pageNow + "/总页数"
				+ pageCount + "<br/>");
		out.println("跳转到<input type='text' id='pageNow' name='pageNow'/><input type='button' onclick='gotoPageNow()' value='跳'/> "); 

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
