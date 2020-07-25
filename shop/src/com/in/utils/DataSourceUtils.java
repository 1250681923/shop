package com.in.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	private static ComboPooledDataSource ds=new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
	
	/**
	 * 鑾峰彇鏁版嵁婧�
	 * @return 杩炴帴姹�
	 */
	public static DataSource getDataSource(){
		return ds;
	}
	
	/**
	 * 浠庣嚎绋嬩腑鑾峰彇杩炴帴
	 * @return 杩炴帴
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		Connection conn = tl.get();
		//鑻ユ槸绗竴娆¤幏鍙� 闇�瑕佷粠姹犱腑鑾峰彇涓�涓繛鎺�,灏嗚繖涓繛鎺ュ拰褰撳墠绾跨▼缁戝畾
		if(conn==null){
			conn=ds.getConnection();
			
			//灏嗚繖涓繛鎺ュ拰褰撳墠绾跨▼缁戝畾
			tl.set(conn);
		}
		
		return conn;
	}
	
	
	
	/**
	 * 閲婃斁璧勬簮
	 * 
	 * @param conn
	 *            杩炴帴
	 * @param st
	 *            璇彞鎵ц鑰�
	 * @param rs
	 *            缁撴灉闆�
	 */
	public static void closeResource(Connection conn, Statement st, ResultSet rs) {
		closeResultSet(rs);
		closeStatement(st);
		closeConn(conn);
	}

	/**
	 * 閲婃斁杩炴帴
	 * 
	 * @param conn
	 *            杩炴帴
	 */
	public static void closeConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				//鍜屽綋鍓嶇嚎绋嬭В缁�
				tl.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}

	}

	/**
	 * 閲婃斁璇彞鎵ц鑰�
	 * 
	 * @param st
	 *            璇彞鎵ц鑰�
	 */
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			st = null;
		}

	}

	/**
	 * 閲婃斁缁撴灉闆�
	 * 
	 * @param rs
	 *            缁撴灉闆�
	 */
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}

	}
	
	/**
	 * 寮�濮嬩簨鍔�
	 * @throws SQLException 
	 */
	public static void startTransaction() throws SQLException{
		//1.鑾峰彇杩炴帴
		Connection conn=getConnection();
		
		//2.寮�濮�
		conn.setAutoCommit(false);
	}
	
	/**
	 * 浜嬪姟鎻愪氦
	 */
	public static void commitAndClose(){
		try {
			//0.鑾峰彇杩炴帴
			Connection conn = getConnection();
			
			//1.鎻愪氦浜嬪姟
			conn.commit();
			
			//2.鍏抽棴涓旂Щ闄�
			closeConn(conn);
		} catch (SQLException e) {
		}
		
	}
	
	/**
	 * 鎻愪氦鍥為【
	 */
	public static void rollbackAndClose(){
		try {
			//0.鑾峰彇杩炴帴
			Connection conn = getConnection();
			
			//1.浜嬪姟鍥為【
			conn.rollback();
			
			//2.鍏抽棴涓旂Щ闄�
			closeConn(conn);
		} catch (SQLException e) {
		}
		
	}
}
