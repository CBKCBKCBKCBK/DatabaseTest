package tw.com.pcschool.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * @author cbk
 *
 */
public class DBUtils {
	private String jdbcurl="jdbc:mysql://localhost:3306/";
	private String defSchema="";	//要使用的資料庫名稱
	//連線選項 ServerTimezone:時區		characterEncoding:資料編碼
	private String jdbcparam="?serverTimezone=UTC&characterEncoding=UTF-8"; 
	private String dbUser="root";	//資料庫帳號
	private String dbPass="";		//資料庫密碼
	
	public DBUtils() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
			// TODO: handle exception
		}
		
	}
//	public DBUtils(String dbUser,String dbPass,String defSchema) {
//		this.dbUser = dbUser;
//		this.dbPass=dbPass;
//		this.defSchema=defSchema;
//	}
	private static DBUtils instance;
	public static DBUtils getInstance() {
		synchronized (DBUtils.class) {
			if (instance==null) {
				instance=new DBUtils();
			}
		}
		return instance;
	}
	public Connection getConnection() throws SQLException{
		Connection conn = DriverManager.getConnection(
				jdbcurl+defSchema+jdbcparam, 
				dbUser, 
				dbPass);
		return conn;	//return new DBUtils;
	}
	public void close(ResultSet rs,PreparedStatement ps,Statement st,Connection conn) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
