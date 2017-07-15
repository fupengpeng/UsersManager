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
 * @date 2017��7��15�� ����11:01:14
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
		//�����û��ύ���û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("�û�����"+username + "   ���룺"+ password);
		
		
		
		//���ݿ�ƥ�����ݣ��ж��Ƿ�����û���������
		if(
//				"fupengpeng".endsWith(username) &&
				
				"123456".equals(password)){
			//�˺�����ƥ���ϣ���ת����¼�ɹ����棨servlet�ṩ�����ַ�����Sendredirct--ת��    forward--ת����
			// sendRedirect("url");  url:/Ӧ����/servlet��url
			//Servlet֮�䴫�����ݷ�ʽ1��ʹ�þ�̬��
//			MyData.name = username;
			//Servlet֮�䴫�����ݷ�ʽ2��sendRedirect��������ƴ�Ӳ����������Դ��ݶ���
//			response.sendRedirect("/UsersManager/MainFrameServlet?username="+username+"&password="+password);
			//Servlet֮�䴫�����ݷ�ʽ3��ʹ��session������,���Դ��ݶ���
			
			request.getSession().setAttribute("username", username);
		
			User user = new User();
			user.setUsername("hahah");
			user.setPassword("222222");
			request.getSession().setAttribute("userobj", user);
			
			response.sendRedirect("/UsersManager/MainFrameServlet");
			
		}else{
			//�˺�������󣬵�����ʾ�����ص�¼����
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
