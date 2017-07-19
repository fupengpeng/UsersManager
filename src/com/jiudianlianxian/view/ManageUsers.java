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
 * Description: �û�����servlet
 * Company: �����ŵ�������Ϣ�������޹�˾
 * ProjectName: UsersManager
 * @author fupengpeng
 * @date 2017��7��18�� ����9:52:28
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

		
		

		// �����ҳ����
		int pageNow = 1; // ��ǰҳ
		// �����û������pageNow
		String sPageNow = request.getParameter("pageNow");
		if (sPageNow != null) {
			pageNow = Integer.parseInt(sPageNow);
		}

		int pageSize = 3; // ָ��ÿҳ��ʾ3����¼
		int pageCount = 1; // �������������

		UsersService usersService = new UsersService();
		ArrayList<User> al = usersService.getUsersByPage(pageNow, pageSize);
		pageCount = usersService.getPageCount(pageSize);

		// 5.��������ƥ�����ݣ��ж��Ƿ��¼�ɹ�
		out.println("<script type='text/javascript' language='javascript'> ");
		out.println("function gotoPageNow(){ "
				+ "var pageNow = document.getElementById('pageNow'); "
				// + "window.alert('pageNow='+pageNow.value)"
				+ "window.open('/UsersManager/ManageUsers?pageNow='+pageNow.value,'_self');"
				+ "} ");
		out.println("</script> ");
		out.println("<h2>�����û�</h2><a href='/UsersManager/MainFrameServlet'>����������</a><br/><br/><a href='/UsersManager/MainFrameServlet'>��ȫ�˳�</a><br/>");
		out.println("<table border='1' bordercolor='green' width='500px'><br/>");
		out.println("<tr><th>uid</th><th>�û���</th><th>phonenumber</th><th>lication</th><th>detailedaddress</th><th>�h���Ñ�</th><th>�޸��Ñ�</th></tr><br/>");

		for (User user : al) {
			out.println("<tr><td>" + user.getUid() + "</td>"+ "<td>" 
		            + user.getUsername() + "</td>" + "<td>"
					+ user.getPhonenumber() + "</td>" + "<td>"
					+ user.getLocation() + "</td>" + "<td>"
					+ user.getDetailedaddress() + "</td>"
							+ "<td><a href='/UsersManager/DelClServlet?id="+user.getUid()+"'>�h���Ñ�</a></td>"
							+ "<td><a href='/UsersManager/ManageUsers'>�޸��Ñ�</a></td>  </tr><br/>");
		}
		out.println("</table><br/>");

		// ��ʾ��ҳ����
		// ��һҳ
		if (pageNow != 1) {
			out.println("<a href='/UsersManager/ManageUsers?pageNow="
					+ (pageNow - 1) + "'>��һҳ</a>");
		}

		// ��ҳ
		for (int i = 1; i <= pageCount; i++) {
			System.out.println(pageCount + "--" + i);
			out.println("<a href='/UsersManager/ManageUsers?pageNow=" + i
					+ "'><" + i + "></a>");
		}
		// ��һҳ
		if (pageNow != pageCount) {
			out.println("<a href='/UsersManager/ManageUsers?pageNow="
					+ (pageNow + 1) + "'>��һҳ</a>");
		}
		// ��ʾ��ҳ��Ϣ
		out.println("<br/>&nbsp;&nbsp;&nbsp;��ǰҳ" + pageNow + "/��ҳ��"
				+ pageCount + "<br/>");
		out.println("��ת��<input type='text' id='pageNow' name='pageNow'/><input type='button' onclick='gotoPageNow()' value='��'/> "); 

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
