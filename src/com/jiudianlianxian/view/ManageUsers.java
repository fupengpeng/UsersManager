package com.jiudianlianxian.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ManageUsers
 * <p>Title: ManageUsers</p>
 * <p>Description: �û�����servlet</p>
 * <p>Company: �����ŵ�������Ϣ�������޹�˾</p>
 * <p>ProjectName: UsersManager</p>
 * @author fupengpeng
 * @date 2017��7��18�� ����9:52:28
 *
 */
public class ManageUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageUsers() {
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
		
		//�����ݿ��л�ȡ���ݲ�չʾ
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String result = "";
		
		//�����ҳ����
		int pageNow = 1;  //��ǰҳ
		//�����û������pageNow
		String sPageNow = request.getParameter("pageNow");
		if(sPageNow != null){
			pageNow = Integer.parseInt(sPageNow);
		}
		
		int pageSize = 3;  //ָ��ÿҳ��ʾ3����¼
		int pageCount = 1;  //�������������
		int rowCount = 1;  //���м�����¼�����ݿ��ѯ
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 2.�õ�����
			connection =
			// jdbc:oracle:this:@127.0.0.1:1521:ORCLHSP
			DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/shoppingmall", "root", "root");

			
			//��ѯ���ݿ�,�������ݹ��ж���ҳ
			preparedStatement = connection.prepareStatement("SELECT * from user");
			resultSet = preparedStatement.executeQuery();
//			resultSet.next();
//			rowCount = resultSet.getInt(1);
			while(resultSet.next()){
				rowCount = resultSet.getInt(1);
			}
			pageCount = (rowCount - 1)/pageSize+1;
			System.out.println("pageCount=="+pageCount);
			
			
			// ����URLΪ jdbc:mysql//��������ַ/���ݿ��� �������2�������ֱ��ǵ�½�û���������
			// 3.����Statement
			statement = connection.createStatement();
			//4.��ѯ���ݿ��ȡ����
//			resultSet = statement.executeQuery("select * from user");
			//չʾpageNowҳ������
			resultSet = statement.executeQuery("SELECT * from user WHERE id<="+pageSize*pageNow+" and id>="+(pageSize*(pageNow-1)+1)+"; ");

//			preparedStatement = connection.prepareStatement("SELECT * from user WHERE id<="+pageSize*pageNow+" and id>="+(pageSize*(pageNow-1)+1)+";");
//			resultSet = preparedStatement.executeQuery();
			
			//5.��������ƥ�����ݣ��ж��Ƿ��¼�ɹ�
			out.println("<script type='text/javascript' language='javascript'> ");
			out.println("function gotoPageNow(){ "
					+ "var pageNow = document.getElementById('pageNow'); "
//					+ "window.alert('pageNow='+pageNow.value)"
					+ "window.open('/UsersManager/ManageUsers?pageNow='+pageNow.value,'_self');"
					+ "} ");
			out.println("</script> ");
			out.println("<h2>�����û�</h2><a href='/UsersManager/MainFrameServlet'>����������</a><br/><br/><a href='/UsersManager/MainFrameServlet'>��ȫ�˳�</a><br/>");
			out.println("<table border='1' bordercolor='green' width='500px'><br/>");
			out.println("<tr><th>uid</th><th>�û���</th><th>phonenumber</th><th>lication</th><th>detailedaddress</th></tr><br/>");
			while(resultSet.next()){
				out.println("<tr><td>"+resultSet.getString(2)+"</td>"
						+ "<td>"+resultSet.getString(3)+"</td>"
						+ "<td>"+resultSet.getString(5)+"</td>"
						+ "<td>"+resultSet.getString(6)+"</td>"
						+ "<td>"+resultSet.getString(7)+"</td></tr><br/>");
			}
			out.println("</table><br/>");
			
			//��ʾ��ҳ����
			//��һҳ
			if(pageNow != 1){
				out.println("<a href='/UsersManager/ManageUsers?pageNow="+(pageNow-1)+"'>��һҳ</a>");
			}
			
			//��ҳ
			for (int i = 1; i <= pageCount; i++) {
				System.out.println(pageCount  +"--"+i);
				out.println("<a href='/UsersManager/ManageUsers?pageNow="+i+"'><"+i+"></a>");
			}
			//��һҳ
			if(pageNow != pageCount){
				out.println("<a href='/UsersManager/ManageUsers?pageNow="+(pageNow+1)+"'>��һҳ</a>");
			}
			//��ʾ��ҳ��Ϣ
			out.println("<br/>&nbsp;&nbsp;&nbsp;��ǰҳ"+pageNow+"/��ҳ��"+pageCount+"<br/>");
			out.println("��ת��<input type='text' id='pageNow' name='pageNow'/><input type='button' onclick='gotoPageNow()' value='��'/> ");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(resultSet != null){
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resultSet = null;
			}
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				statement = null;
			}
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				connection = null;
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
