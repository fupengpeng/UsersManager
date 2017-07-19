package com.jiudianlianxian.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiudianlianxian.domain.User;
import com.jiudianlianxian.service.UsersService;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// �����û��ύ���û���������
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		// ѧϰ����
//		test(request, response, username, password);

		UsersService usersService = new UsersService();
		User user = new User();
		user.setAccount(account);
		user.setPassword(password);
		if (usersService.checkUser(user)) {

			request.getRequestDispatcher("/MainFrameServlet").forward(request, response);
			System.out.println("�˺�����ƥ�䣬��¼�ɹ�");
		} else {
			request.setAttribute("error", "�û��������������");
//			request.getRequestDispatcher("/LoginServlet").forward(request, response);
			System.out.println("�˺����벻ƥ�䣬��¼ʧ��");
		}


	}

	private void test(HttpServletRequest request, HttpServletResponse response,
			String username, String password) throws ServletException,
			IOException {
		String url = request.getRequestURL().toString(); // ����������ַ
		System.out.println("���������ַURL��" + url);
		String uri = request.getRequestURI(); // �����uri
		System.out.println("uri:" + uri);
		String queryString = request.getQueryString(); // ������ֶ�
		System.out.println("queryString:" + queryString);

		// ��ȡ���󷽵�������
		String host = request.getRemoteHost();
		System.out.println("��������" + host);

		// ��ȡ���󷽵�����˿ں�
		int port = request.getRemotePort();
		System.out.println("���󷽵�����˿ں�port:" + port);
		// ��ȡ����web��������ʹ�õ�����˿ں�
		String localPort = request.getRemoteHost();
		System.out.println("����web��������ʹ�õ�����˿ں�localPort��" + host);
		// ��ȡ����web������ip��ַ
		String localAddr = request.getLocalAddr();
		System.out.println("����web������ip��ַlocalAddr��" + localAddr);
		// ��ȡ����web������������
		String localName = request.getLocalAddr();
		System.out.println("����web������������localName��" + localName);

		if (request.getHeader("x-forwarded-for") == null) {

			// String remoteAddrString = request.getRemoteAddr();
			System.out.println("remoteAddrString==" + request.getRemoteAddr());
		} else {
			System.out.println("δ��ȡ��ip" + request.getHeader("x-forwarded-for"));

		}

		// //ʵ�ֽ�ֹ��ǰ�û��ĵ�¼
		// String remoteAddrString = request.getRemoteAddr();
		// if(remoteAddrString.equals("192.168.1.40")){ //�������ip�ǣ�192.168.1.40����
		// ��ֹ����
		//
		// System.out.println("remoteAddrString=="+remoteAddrString);
		// response.sendRedirect("/UsersManager/Error");
		//
		//
		// }

		// ���ݿ�ƥ�����ݣ��ж��Ƿ�����û���������
		if (
		// "fupengpeng".endsWith(username) &&

		"111111".equals(password)) {
			// �˺�����ƥ���ϣ���ת����¼�ɹ����棨servlet�ṩ�����ַ�����Sendredirct--ת�� forward--ת����
			// sendRedirect("url"); url:/Ӧ����/servlet��url
			// Servlet֮�䴫�����ݷ�ʽ1��ʹ�þ�̬��
			// MyData.name = username;
			// Servlet֮�䴫�����ݷ�ʽ2��sendRedirect��������ƴ�Ӳ����������Դ��ݶ���
			// response.sendRedirect("/UsersManager/MainFrameServlet?username="+username+"&password="+password);
			// Servlet֮�䴫�����ݷ�ʽ3��ʹ��session������,���Դ��ݶ���

			// request.getSession().setAttribute("username", username);
			//
			// User user = new User();
			// user.setUsername("hahah");
			// user.setPassword("222222");
			// request.getSession().setAttribute("userobj", user);
			//
			// response.sendRedirect("/UsersManager/MainFrameServlet");

			// Servlet֮�䴫�����ݷ�ʽ4��ת����ʽ��ʹ��request��setAttribute����
			// ��username���뵽�����
			request.setAttribute("username", username);
			// ��ʾʹ��ת���ķ�����request��response���󴫵ݸ���һ��servlet
			request.getRequestDispatcher("/MainFrameServlet").forward(request,
					response);
			 // ������

		} else {
			// �˺�������󣬵�����ʾ�����ص�¼����
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
