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
 * <p>Description: ��¼�ɹ����������</p>
 * <p>Company: </p>
 * @author fupengpeng
 * @date 2017��7��15�� ����11:17:54
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
		//Servlet֮�䴫�����ݷ�ʽ1��ʹ�þ�̬��
//		out.println("<h1>������</h1>"+"�Ñ�����"+MyData.name);
		
		//Servlet֮�䴫�����ݷ�ʽ2��sendRedirect��������ƴ�Ӳ���
		//�@ȡ�����
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		out.println("<h1>������</h1>"+"�Ñ�����"+username+"   �ܴa��"+password);
		//Servlet֮�䴫�����ݷ�ʽ3��ʹ��session������
		String usernameSession = (String) request.getSession().getAttribute("username");
		out.println("<h1>������</h1>"+"�Ñ���session��"+usernameSession);
		//��ȡsession�еĶ���
		User user = (User) request.getSession().getAttribute("userobj");
		out.println("<h1>������</h1>"+"�Ñ���--User��"+user.getUsername()+"   ����--User��"+user.getPassword());
		
		out.println("<a href='/UsersManager/LoginServlet'>���ص�¼�������µ�¼</a>");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
