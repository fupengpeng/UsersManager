package com.jiudianlianxian.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 * <p>Title: LoginServlet</p>
 * <p>Description: ��¼Servlet</p>
 * <p>Company: </p>
 * @author fupengpeng
 * @date 2017��7��15�� ����10:42:09
 *
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//����һ�����棨html��
		out.println("<h1>�û���¼</h1>");
		//action˵����/webӦ����/Servlet��url
		out.println("<form action='/UsersManager/LoginClServlet' method='post'>");
		out.println("�û���<input type='text' name='username'/><br/>");
		out.println("�ܡ���<input type='password' name='password'/><br/>");
		out.println("<input type='submit' value='��¼'/><br/>");
		out.println("</form>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
