package com.jiudianlianxian.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyInfoForm
 */
public class MyInfoForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h1>�û������ʾ�</h1>");
		//action˵����/webӦ����/Servlet��url
		out.println("<form action='/UsersManager/RegisterCLServlet' method='post'>");
		out.println("�û���<input type='text' name='username'/><br/>");
		out.println("�ܡ���<input type='password' name='password'/><br/>");
		out.println("�ܡ���<input type='radio' name='sex' value='��'/>��<input type='radio' name='sex' value='Ů'/>Ů<br/>");
		out.println("����<input type='checkbox' name='hobby' value='����'/>���� <input type='checkbox' name='hobby' value='����'/>���� <input type='checkbox' name='hobby' value='����'/>����<br/>");
		out.println("���ڳ��У�<select name='city'><option value='beijing'>����</option><option value='chongqing'>����</option></select><br/>");
		out.println("���˽��ܣ�<textarea cols='20' rows='10' name='intro'></textarea><br/>");
		out.println("�ύ��Ƭ��<input type='file' name='photo'/><br/>");
		//hiddenʹ�ã�1.��ϣ���û����������ݡ�2.��Ӱ����棬ͬʱҵ���߼�Ҫʹ�ô�����
		out.println("<input type='hidden' value='abc' name='hidde01'/><br/>");
		out.println("<input type='submit' value='�ύ��Ϣ'/><br/>");
		out.println("</form>");

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
