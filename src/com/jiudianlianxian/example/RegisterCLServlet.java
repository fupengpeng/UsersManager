package com.jiudianlianxian.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterCLServlet
 */
public class RegisterCLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCLServlet() {
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
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String[] hobbys = request.getParameterValues("hobby");
		String city = request.getParameter("city");
		String intro = request.getParameter("intro");
		String hidden = request.getParameter("hidden01");
		
		out.println("�û���="+username+"</br>");
		out.println("�ܡ���="+password+"</br>");
		out.println("�ԡ���="+sex+"</br>");
		if(hobbys!=null){
			for (int i = 0; i < hobbys.length; i++) {
				out.println("���ã�"+hobbys[i]+"</br>");
			}
		}else{
			out.println("��ʱû��ѡ�񰮺�");
		}
		out.println("���ڳ��У�"+city+"</br>");
		out.println("��ϸ���ܣ�"+intro+"</br>");
		out.println("hidden���ݵ����ݣ�"+hidden+"</br>");
		
//		for (String string : hobby) {
//			out.println("���ã�"+string);
//		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
