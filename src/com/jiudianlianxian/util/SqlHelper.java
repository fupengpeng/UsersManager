package com.jiudianlianxian.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 
 * Title: SqlHelper Description: ���ݿ���������� Company: �����ŵ�������Ϣ�������޹�˾ ProjectName:
 * UsersManager
 * 
 * @author fupengpeng
 * @date 2017��7��19�� ����11:34:20
 *
 */
public class SqlHelper {

	// ��������Ҫ�ı���
	private static Connection ct = null;

	// ���������£�ʹ�õ���PreparedStatement�����Statement��Ŀ����Ϊ�˷�ֹsqlע��
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static CallableStatement cs = null;

	// �������ݿ�Ĳ���
	private static String url = "";
	private static String username = "";
	private static String driver = "";
	private static String password = "";
	// ��ȡ�����ļ�������
	private static Properties pp = null;
	private static InputStream fis = null;

	// ����������ֻ��Ҫһ��
	static {
		try {
			// ��dbinfo��properties�ļ��ж�ȡ������Ϣ
			pp = new Properties();
			// ʹ��web��Ŀʱ��ʹ���������,���������ȡ��Դʱ��Ĭ�϶�ȡ��Ŀ¼��src
			fis = SqlHelper.class.getClassLoader().getResourceAsStream(
					"dbinfo.properties");
			pp.load(fis);
			// ��ȡ�����ļ�����Ĳ���
			url = pp.getProperty("url");
			username = pp.getProperty("username");
			driver = pp.getProperty("driver");
			password = pp.getProperty("password");
			Class.forName(driver);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fis = null;
		}
	}

	// �õ�����
	public static Connection getConnection() {
		try {
			ct = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ct;
	}

	// ��ҳ���⣿������������
	public static ResultSet executeQuery2() {
		return null;
	}

	// ���ô洢���̣��з���Result�����Ĵ洢����
	// sql call ����
	public static CallableStatement callPro2(String sql, String[] inparameters,
			Integer[] outparmeters) {
		try {
			ct = getConnection();
			cs = ct.prepareCall(sql);
			if (inparameters != null) {
				for (int i = 0; i < inparameters.length; i++) {
					cs.setObject(i + 1, inparameters[i]);
				}
			}
			// ��out������ֵ
			if (outparmeters != null) {
				for (int i = 0; i < outparmeters.length; i++) {
					// TODO setObject�������޸�
					cs.setObject(inparameters.length + i + 1, inparameters[i]);
				}
			}

			cs.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			// ����Ҫ�ر�
		}
		return cs;
	}

	// ���ô洢����
	// sql �� {call ����(?,?,?)}
	public static void callPro1(String sql, String[] parameters) {

		try {
			ct = getConnection();
			cs = ct.prepareCall(sql);

			// �� ��ֵ
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					cs.setObject(i + 1, parameters[i]);
				}
			}
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			close(rs, cs, ct);
		}
	}

	// ͳһ��SELECT----oracle
	// ResultSet->Array
	public static ResultSet executeQueryOracle(String sql, String[] parameters) {

		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			if (parameters != null && parameters.equals("")) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			// close(rs,cs,ct);
		}

		return rs;

	}

	// ͳһ��SELECT
	// ResultSet->Array
	public static ResultSet executeQuery(String sql) {

		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);

			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			// close(rs, cs, ct);
		}

		return rs;

	}

	// ����ж��sql��� update/delete /insert [��Ҫ����������]
	public static void executeUpdate2(String[] sql, String[][] parameters) {
		try {
			// ����
			// 1.�������
			ct = getConnection();

			// �����sql�������Ƕ����
			ct.setAutoCommit(false);
			for (int i = 0; i < sql.length; i++) {
				if (parameters[i] != null) {
					ps = ct.prepareStatement(sql[i]);
					for (int j = 0; j < parameters[i].length; j++) {
						ps.setString(j + 1, parameters[i][j]);
					}
					ps.executeUpdate();
				}
			}
			ct.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				// �ع�
				ct.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());

		} finally {
			close(rs, ps, ct);
		}
	}

	// ֻ��һ����� update/delete /insert
	// sql ��ʽ��update ���� set �ֶ���=�� where �ֶ�=��
	// parameters
	public static void executeUpdateOracle(String sql, String[] parameters) {
		try {
			// ����һ��ps
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			// ������ֵ
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}
			// ִ��
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());

		} finally {
			close(rs, ps, ct);
		}

	}

	// ֻ��һ����� update/delete /insert
	// sql ��ʽ��update ���� set �ֶ���=�� where �ֶ�=��
	// parameters
	public static void executeUpdate(String sql) {
		try {
			// ����һ��ps
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			System.out.println("sql=="+sql);
			// ִ��
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());

		} finally {
			close(rs, ps, ct);
		}

	}

	// �ر���Դ�ĺ���
	public static void close(ResultSet rs, Statement ps, Connection ct) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (ct != null) {
			try {
				ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static Connection getCt() {
		return ct;
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static CallableStatement getCs() {
		return cs;
	}

}
