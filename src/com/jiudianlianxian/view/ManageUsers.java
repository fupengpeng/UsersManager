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
 * <p>Description: 用户管理servlet</p>
 * <p>Company: 济宁九点连线信息技术有限公司</p>
 * <p>ProjectName: UsersManager</p>
 * @author fupengpeng
 * @date 2017年7月18日 上午9:52:28
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
		
		//从数据库中获取数据并展示
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String result = "";
		
		//定义分页变量
		int pageNow = 1;  //当前页
		//接收用户点击的pageNow
		String sPageNow = request.getParameter("pageNow");
		if(sPageNow != null){
			pageNow = Integer.parseInt(sPageNow);
		}
		
		int pageSize = 3;  //指定每页显示3条记录
		int pageCount = 1;  //根据请款计算出来
		int rowCount = 1;  //共有几条记录，数据库查询
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 2.得到连接
			connection =
			// jdbc:oracle:this:@127.0.0.1:1521:ORCLHSP
			DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/shoppingmall", "root", "root");

			
			//查询数据库,计算数据共有多少页
			preparedStatement = connection.prepareStatement("SELECT * from user");
			resultSet = preparedStatement.executeQuery();
//			resultSet.next();
//			rowCount = resultSet.getInt(1);
			while(resultSet.next()){
				rowCount = resultSet.getInt(1);
			}
			pageCount = (rowCount - 1)/pageSize+1;
			System.out.println("pageCount=="+pageCount);
			
			
			// 连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码
			// 3.创建Statement
			statement = connection.createStatement();
			//4.查询数据库获取数据
//			resultSet = statement.executeQuery("select * from user");
			//展示pageNow页的数据
			resultSet = statement.executeQuery("SELECT * from user WHERE id<="+pageSize*pageNow+" and id>="+(pageSize*(pageNow-1)+1)+"; ");

//			preparedStatement = connection.prepareStatement("SELECT * from user WHERE id<="+pageSize*pageNow+" and id>="+(pageSize*(pageNow-1)+1)+";");
//			resultSet = preparedStatement.executeQuery();
			
			//5.便利数据匹配数据，判断是否登录成功
			out.println("<script type='text/javascript' language='javascript'> ");
			out.println("function gotoPageNow(){ "
					+ "var pageNow = document.getElementById('pageNow'); "
//					+ "window.alert('pageNow='+pageNow.value)"
					+ "window.open('/UsersManager/ManageUsers?pageNow='+pageNow.value,'_self');"
					+ "} ");
			out.println("</script> ");
			out.println("<h2>管理用户</h2><a href='/UsersManager/MainFrameServlet'>返回主界面</a><br/><br/><a href='/UsersManager/MainFrameServlet'>安全退出</a><br/>");
			out.println("<table border='1' bordercolor='green' width='500px'><br/>");
			out.println("<tr><th>uid</th><th>用户名</th><th>phonenumber</th><th>lication</th><th>detailedaddress</th></tr><br/>");
			while(resultSet.next()){
				out.println("<tr><td>"+resultSet.getString(2)+"</td>"
						+ "<td>"+resultSet.getString(3)+"</td>"
						+ "<td>"+resultSet.getString(5)+"</td>"
						+ "<td>"+resultSet.getString(6)+"</td>"
						+ "<td>"+resultSet.getString(7)+"</td></tr><br/>");
			}
			out.println("</table><br/>");
			
			//显示分页数据
			//上一页
			if(pageNow != 1){
				out.println("<a href='/UsersManager/ManageUsers?pageNow="+(pageNow-1)+"'>上一页</a>");
			}
			
			//分页
			for (int i = 1; i <= pageCount; i++) {
				System.out.println(pageCount  +"--"+i);
				out.println("<a href='/UsersManager/ManageUsers?pageNow="+i+"'><"+i+"></a>");
			}
			//下一页
			if(pageNow != pageCount){
				out.println("<a href='/UsersManager/ManageUsers?pageNow="+(pageNow+1)+"'>下一页</a>");
			}
			//显示分页信息
			out.println("<br/>&nbsp;&nbsp;&nbsp;当前页"+pageNow+"/总页数"+pageCount+"<br/>");
			out.println("跳转到<input type='text' id='pageNow' name='pageNow'/><input type='button' onclick='gotoPageNow()' value='跳'/> ");
			
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
