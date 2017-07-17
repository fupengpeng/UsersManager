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
		
		out.println("用户名="+username+"</br>");
		out.println("密　码="+password+"</br>");
		out.println("性　别="+sex+"</br>");
		if(hobbys!=null){
			for (int i = 0; i < hobbys.length; i++) {
				out.println("爱好："+hobbys[i]+"</br>");
			}
		}else{
			out.println("暂时没有选择爱好");
		}
		out.println("所在城市："+city+"</br>");
		out.println("详细介绍："+intro+"</br>");
		out.println("hidden传递的数据："+hidden+"</br>");
		
//		for (String string : hobby) {
//			out.println("爱好："+string);
//		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
