package com.jiudianlianxian.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jiudianlianxian.domain.User;
import com.jiudianlianxian.util.SqlHelper;

/**
 * 
 * Title: UsersService
 * Description: User�����Ĕ�����̎���
 * Company: �����ŵ�������Ϣ�������޹�˾
 * ProjectName: UsersManager
 * @author fupengpeng
 * @date 2017��7��19�� ����9:17:44
 *
 */
public class UsersService {
	
	
	/**
	 * 
	 * Description: ����û�
	 * @param user
	 * @return
	 */
	public boolean addUser(User user){
		boolean b = true;
		String sql = "INSERT INTO user("
				+ "id,uid,username,sex,phonenumber,"
				+ "location,detailedaddress,postcode,birthday,wechat,"
				+ "growthvalue,account,password,integral,isdefaultaddress) "
				+ "VALUES("
				+ user.getId()+","+user.getUid()+","+user.getUsername()+","+user.getSex()+","+user.getPhonenumber()+","
				+ user.getLocation()+","+user.getDetailedaddress()+","+user.getPostcode()+","+user.getBirthday()+","+user.getWechat()+","
				+ user.getGrowthvalue()+","+ user.getAccount()+","+user.getPassword()+","+user.getIntegral()+","+user.getIsdefaultaddress()+") ;";
		try {
			SqlHelper.executeUpdate(sql);
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 
	 * Description: �޸��û�
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user){
		boolean b = true;
		String sql = "UPDATE user SET "
				+ "username='"+user.getUsername()+"' , "
				+ "sex='"+user.getSex()+"' ,  "
				+ "phonenumber='"+user.getPhonenumber()+"' ,  "
				+ "location='"+user.getLocation()+"' ,  "
				+ "detailedaddress='"+user.getDetailedaddress()+"' ,  "
				+ "postcode='"+user.getPostcode()+"' ,  "
				+ "birthday='"+user.getBirthday()+"' ,  "
				+ "wechat='"+user.getWechat()+"' ,  "
				+ "growthvalue='"+user.getGrowthvalue()+"' ,  "
				+ "account='"+user.getAccount()+"' ,  "
				+ "password='"+user.getPassword()+"' ,  "
				+ "integral='"+user.getIntegral()+"' ,  "
				+ "isdefaultaddress='"+user.getIsdefaultaddress()+"' "
				+ "WHERE uid='"+user.getUid()+"';";
		try {
			SqlHelper.executeUpdate(sql);
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		
		
		return b;
	}
	
	/**
	 * 
	 * Description: ���ݸ�����uid��ѯ���ݿ�����
	 * @param uid
	 * @return
	 */
	public User getUserByUid (String uid){
		
		User user = new User();
		String sql = "select * from user where uid='"+uid+"'";
		ResultSet rs = SqlHelper.executeQuery(sql);
		try {
			// ��ѯ���ݿ�,��ȡ����uid��Ӧ������
			while (rs.next()) {
				
				user.setId(rs.getInt(1));
				user.setUid(rs.getString(2));
				user.setUsername(rs.getString(3));
				user.setSex(rs.getString(4));
				user.setPhonenumber(rs.getString(5));
				user.setLocation(rs.getString(6));
				user.setDetailedaddress(rs.getString(7));
				user.setPostcode(rs.getString(8));
				user.setBirthday(rs.getString(9));
				user.setWechat(rs.getString(10));
				user.setGrowthvalue(rs.getString(11));
				user.setAccount(rs.getString(12));
				user.setPassword(rs.getString(13));
				user.setIntegral(rs.getString(14));
				user.setIsdefaultaddress(rs.getString(15));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		
		return user;
		
	}

	/**
	 * 
	 * Description: ���ݸ�����uid�h���Ñ�
	 * @param uid
	 * @return
	 */
	public boolean delUser(String uid){
		boolean b = true;
		String sql = "delete from user where uid = '"+uid+"'";
		
		try {
			System.out.println("uid---"+uid);
			SqlHelper.executeUpdate(sql);
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		
		return b;
	}
	/**
	 * 
	 * Description: ��ȡpageCount
	 * @param pageSize
	 * @return
	 */
	public int getPageCount(int pageSize) {
		int rowCount = 0;
		ResultSet rs = null;
		String sql = "SELECT * from user";

		try {
			rs = SqlHelper.executeQuery(sql);

			// ��ѯ���ݿ�,�������ݹ��ж���ҳ
			while (rs.next()) {
				rowCount = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}
		return (rowCount - 1) / pageSize + 1;

	}

	/**     
	 * @return 
	 * Description:  ���շ�ҳ����ȡ�û�
	 * @param pageNow  ��ǰҳ
	 * @param pageSize  ��ǰҳ��ʾ��������
	 * @return  �û����ݶ��󼯺�
	 */
	public ArrayList<User> getUsersByPage(int pageNow, int pageSize) {
		ArrayList<User> al = new ArrayList<User>();

		String sql = "SELECT * from user WHERE id<=" + pageSize * pageNow
				+ " and id>=" + (pageSize * (pageNow - 1) + 1) + "; ";

		ResultSet rs = SqlHelper.executeQuery(sql);
		// ���η�װ����ResultSet---->User����---->ArrayList����
		try {
			while (rs.next()) {
				User user = new User();
				user.setUid(rs.getString(2));
				user.setUsername(rs.getString(3));
				user.setPhonenumber(rs.getString(5));
				user.setLocation(rs.getString(6));
				user.setDetailedaddress(rs.getString(7));
				al.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getConnection());
		}

		return al;
	}

	/**
	 * Description: ��¼��֤
	 * @param user
	 * @return  ��¼�Ƿ�ɹ�
	 */
	public boolean checkUser(User user) {

		boolean b = false;
		// //1.�������ݿ�
		// Connection ct = null;
		// PreparedStatement ps = null;
		// ResultSet rs = null;
		//
		// try {
		//
		// Class.forName("com.mysql.jdbc.Driver");
		// ct =
		// DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingmall",
		// "root", "root");
		// //SELECT * FROM user WHERE account='15858585959' and
		// password='100101'
		// // ps =
		// ct.prepareStatement("SELECT * from user WHERE 	account='"+user.getAccount()+"' and password='"+user.getPassword()+"'");
		// ps = ct.prepareStatement("SELECT * FROM user ");
		//
		// //������ֵ
		// rs = ps.executeQuery();
		//
		// if(rs.next()){
		// b = true;
		// }
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }finally{
		// if(rs != null){
		// try {
		// rs.close();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// rs = null;
		// }
		// if(ps != null){
		// try {
		// ps.close();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// ps = null;
		// }
		// if(ct != null){
		// try {
		// ct.close();
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// ct = null;
		// }
		// }

		// ʹ��SqlHelper��ʵ�����ݿ����
		String sql = "SELECT * FROM user";
		ResultSet rs = SqlHelper.executeQuery(sql);
		try {
			if (rs.next()) {
				b = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}

		return b;
	}

}
