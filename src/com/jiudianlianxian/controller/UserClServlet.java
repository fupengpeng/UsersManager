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
 * 
 * Title: UserClServlet
 * Description: �Ñ�h��̎���
 * Company: �����ŵ�������Ϣ�������޹�˾
 * ProjectName: UsersManager
 * @author fupengpeng
 * @date 2017��7��19�� ����5:09:10
 *
 */
public class UserClServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserClServlet() {
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
		UsersService usersService = new UsersService();
		//����
		String type = request.getParameter("type");
		if("delete".equals(type)){
			//����uid
			String uid = (String) request.getParameter("uid");
			//����UsersService�еķ���ɾ��
			System.out.println("type="+type+"   uid="+uid);
			if(usersService.delUser(uid)){
				//ok
				request.getRequestDispatcher("/Ok").forward(request, response);
			}else{
				//error
				request.getRequestDispatcher("/Error").forward(request, response);
			}
		}else if("gotoUpdateView".equals(type)){
			//��ȡҪ�޸ĵ�uid
			String uid = request.getParameter("uid");
			//����uid��ѯ���ݿ⣬��ȡuserBean
			User user = usersService.getUserByUid(uid);
			//����user������һ������
			request.setAttribute("user", user);
			
			request.getRequestDispatcher("/UpdateUserView").forward(request, response);
			
			
			
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
