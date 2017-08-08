package com.jiudianlianxian.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jiudianlianxian.domain.User;
import com.jiudianlianxian.service.UsersService;
import com.jiudianlianxian.utils.HibernateUtils;

/**
 * 
 * Title: UserClServlet
 * Description: �Ñ�����̎���
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
				request.setAttribute("info", "ɾ���ɹ�");
				request.getRequestDispatcher("/Ok").forward(request, response);
			}else{
				//error
				request.setAttribute("info", "ɾ��ʧ��");
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
			
			
			
		}else if("update".equals(type)){
			//�����û��µ���Ϣ
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
			//�ѽ��յ�����Ϣ����װ��һ��User����
			User user = new User(Integer.parseInt(id),uid, username, sex, phonenumber,
					location, detailedaddress, postcode, birthday, wechat, growthvalue,
					account, password, integral, isdefaultaddress);
			
			//�޸��û���Ϣ
			if(usersService.updateUser(user)){
				//ok
				request.setAttribute("info", "�޸ĳɹ�");
				request.getRequestDispatcher("/Ok").forward(request, response);
			}else{
				//error
				request.setAttribute("info", "�޸�ʧ��");
				request.getRequestDispatcher("/Error").forward(request, response);
			}
			
		}else if("gotoAddUser".equals(type)){
			//
			System.out.println("001");
			request.getRequestDispatcher("/AddUserView").forward(request, response);
			
		}else if("add".equals(type)){
//			//�����û��µ���Ϣ
//			String id = request.getParameter("id");
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
//			//�ѽ��յ�����Ϣ����װ��һ��User����
//			User user = new User(Integer.parseInt(id),uid, username, sex, phonenumber,
//					location, detailedaddress, postcode, birthday, wechat,
//					growthvalue,account, password, integral, isdefaultaddress);
//			
//			//����û�
//			if(usersService.addUser(user)){
//				//ok
//				request.setAttribute("info", "��ӳɹ�");
//				request.getRequestDispatcher("/Ok").forward(request, response);
//			}else{
//				//error
//				request.setAttribute("info", "���ʧ��");
//				request.getRequestDispatcher("/Error").forward(request, response);
//			}
			
		
			
			Session session = null;
			Transaction transaction = null;
			try {
				
				//1.��ʹ��SessionFactory����Session����
				//��⣺������jdbc���������ݿ�
				session = HibernateUtils.getSessionObject();
				//2.����������
				transaction = session.beginTransaction();
				//3.��д�����crud����
				
				//4.��д�����crud����
				com.jiudianlianxian.entity.User user = new com.jiudianlianxian.entity.User();
				user.setUid(uid);
				user.setUsername(username);
				user.setSex(sex);
				user.setPhonenumber(phonenumber);
				user.setLocation(location);
				user.setDetailedaddress(detailedaddress);
				user.setPostcode(postcode);
				user.setBirthday(birthday);
				user.setWechat(wechat);
				user.setGrowthvalue(growthvalue);
				user.setAccount(account);
				user.setPassword(password);
				user.setIntegral(integral);
				user.setIsdefaultaddress(isdefaultaddress);
				//����session����ʵ�����
				session.save(user);
				
				//4.���ύ����
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
				//5.�ع�����
				transaction.rollback();
				
				//error
				request.setAttribute("info", "���ʧ��");
				request.getRequestDispatcher("/Error").forward(request, response);
				
				
				
			} finally {
				//6.���ر���Դ     ��ʹ�����뱾���̰߳󶨵�session����֮�󣬾Ͳ���Ҫ�ֶ��ر�session��
//				session.close();
			}
			//ok
			request.setAttribute("info", "��ӳɹ�");
			request.getRequestDispatcher("/Ok").forward(request, response);
			
			
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
