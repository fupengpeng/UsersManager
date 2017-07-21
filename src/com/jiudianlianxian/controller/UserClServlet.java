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
 * Description: 用戶刪除處理類
 * Company: 济宁九点连线信息技术有限公司
 * ProjectName: UsersManager
 * @author fupengpeng
 * @date 2017年7月19日 下午5:09:10
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
		//接收
		String type = request.getParameter("type");
		if("delete".equals(type)){
			//接收uid
			String uid = (String) request.getParameter("uid");
			//调用UsersService中的方法删除
			System.out.println("type="+type+"   uid="+uid);
			if(usersService.delUser(uid)){
				//ok
				request.setAttribute("info", "删除成功");
				request.getRequestDispatcher("/Ok").forward(request, response);
			}else{
				//error
				request.setAttribute("info", "删除失败");
				request.getRequestDispatcher("/Error").forward(request, response);
			}
		}else if("gotoUpdateView".equals(type)){
			//获取要修改的uid
			String uid = request.getParameter("uid");
			//根据uid查询数据库，获取userBean
			User user = usersService.getUserByUid(uid);
			//传递user对象到下一个界面
			request.setAttribute("user", user);
			
			request.getRequestDispatcher("/UpdateUserView").forward(request, response);
			
			
			
		}else if("update".equals(type)){
			//接收用户新的信息
			String id = request.getParameter("id");
			String uid = request.getParameter("uid");
			String username = request.getParameter("username");
			String sex = request.getParameter("sex");
			String phonenumber = request.getParameter("phonenumber");
			String location = request.getParameter("location");
			String detailedaddress = request.getParameter("detailedaddress");
			String postcode = request.getParameter("postcode");
			String birthday = request.getParameter("birthday");
			String wechat = request.getParameter("wechat");
			String growthvalue = request.getParameter("growthvalue");
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			String integral = request.getParameter("integral");
			String isdefaultaddress = request.getParameter("isdefaultaddress");
			//把接收到的信息，封装成一个User对象
			User user = new User(Integer.parseInt(id),uid, username, sex, phonenumber,
					location, detailedaddress, postcode, birthday, wechat, growthvalue,
					account, password, integral, isdefaultaddress);
			
			//修改用户信息
			if(usersService.updateUser(user)){
				//ok
				request.setAttribute("info", "修改成功");
				request.getRequestDispatcher("/Ok").forward(request, response);
			}else{
				//error
				request.setAttribute("info", "修改失败");
				request.getRequestDispatcher("/Error").forward(request, response);
			}
			
		}else if("gotoAddUser".equals(type)){
			//
			
			request.getRequestDispatcher("/AddUserView").forward(request, response);
			
		}else if("add".equals(type)){
			//接收用户新的信息
			String id = request.getParameter("id");
			String uid = request.getParameter("uid");
			String username = request.getParameter("username");
			String sex = request.getParameter("sex");
			String phonenumber = request.getParameter("phonenumber");
			String location = request.getParameter("location");
			String detailedaddress = request.getParameter("detailedaddress");
			String postcode = request.getParameter("postcode");
			String birthday = request.getParameter("birthday");
			String wechat = request.getParameter("wechat");
			String growthvalue = request.getParameter("growthvalue");
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			String integral = request.getParameter("integral");
			String isdefaultaddress = request.getParameter("isdefaultaddress");
			//把接收到的信息，封装成一个User对象
			User user = new User(Integer.parseInt(id),uid, username, sex, phonenumber,
					location, detailedaddress, postcode, birthday, wechat,
					growthvalue,account, password, integral, isdefaultaddress);
			
			//修改用户信息
			if(usersService.addUser(user)){
				//ok
				request.setAttribute("info", "添加成功");
				request.getRequestDispatcher("/Ok").forward(request, response);
			}else{
				//error
				request.setAttribute("info", "添加失败");
				request.getRequestDispatcher("/Error").forward(request, response);
			}
			
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
