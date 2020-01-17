package tw.com.pcschool.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import tw.com.pcschool.config.DBUtils;
import tw.com.pcschool.dao.UserDao;
import tw.com.pcschool.domin.Users;
/**
 * 
 * @author cbk
 *
 */
public class UserDaoImpl implements UserDao {
	

	@Override
	public void addUser(Users users) throws Exception {
		// 連接資料庫
		Connection conn;
		conn=DBUtils.getInstance().getConnection();
		//傳送sql語法
		String sql="insert into users(username,pass,email,birthday)values(?,?,?,?);";
		PreparedStatement ps=conn.prepareStatement(sql);
		//傳送要新增的值
		//ps.setString(1, x);
		ps.setString(1, users.getUsername());
		ps.setString(2, users.getPass());
		ps.setString(3, users.getEmail());
		ps.setDate(4, users.getBirthday());
		ps.executeUpdate();
		DBUtils.getInstance().close(null, ps, null, conn);
	}
	public List<Users> loadUser(List<String> id)throws Exception {
		List<Users> userList=new ArrayList<Users>();
		Users users=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Integer> idList=new ArrayList<Integer>();
		
		try {
			conn=DBUtils.getInstance().getConnection();
			for (String idString: id) {
				idList.add(Integer.parseInt(idString));
			}
			for (int theId: idList) {
				String sql="select * from users where id=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, theId);
				rs=ps.executeQuery();
				if (rs.next()) {
					String username=rs.getString("username");
					String pass=rs.getString("pass");
					String email=rs.getString("email");
					Date birthday=rs.getDate("birthday");
					users=new Users(theId, username, pass, email, birthday);
					userList.add(users);
				}else {
					throw new Exception("error id:"+theId);
				}
			}
			return userList;
		} finally {
			// TODO: handle finally clause
			DBUtils.getInstance().close(rs, ps, null, conn);
		}
	}
	@Override
	public List<Users> readUser(String id, String readData) throws Exception {
		List<Users> userList=new ArrayList<Users>();
		Users users=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet  rs=null;
		id=id.trim();
		readData=readData.trim();
		try {
			conn=DBUtils.getInstance().getConnection();
			if (readData!=null&&readData.trim().length()>0) {
				String sql="select id,username,email,birthday from users "
						+ "where username like ? or email like ? "
						+ "or cast(birthday as char(50))  LIKE ?";
				ps=conn.prepareStatement(sql);
				ps.setString(1,"%"+readData+"%");
				ps.setString(2,"%"+readData+"%");
				ps.setString(3,"%"+readData+"%");
			}else if (id!=null&&id.trim().length()>0) {
				String sql="select id,username,email,birthday from users "
						+ "where id=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1,Integer.parseInt(id));
			}else {
				String sql = "select id,username,email,birthday from users order by id";
				ps = conn.prepareStatement(sql);
			}
			
			rs=ps.executeQuery();
			while(rs.next()) {
				int theId=rs.getInt("id");
				String username=rs.getString("username");
//				String pass=rs.getString("pass");
				String email=rs.getString("email");
				Date birthday=rs.getDate("birthday");
				users=new Users(theId,username, null, email, birthday);
				userList.add(users);
			}
			return userList;
		} finally {
			// TODO: handle finally clause
			DBUtils.getInstance().close(rs, ps, null, conn);
		}
	}

	public void updateUser(List<Users> userList) throws Exception {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=DBUtils.getInstance().getConnection();
			for (Users users : userList) {
				String sql="update users set username=?,pass=?,email=?,birthday=? where id=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, users.getUsername());
				ps.setString(2, users.getPass());
				ps.setString(3, users.getEmail());
				ps.setDate(4, users.getBirthday());
				ps.setInt(5, users.getId());
				ps.execute();
			}
		} finally {
			 DBUtils.getInstance().close(null, ps, null, conn);
		}
	}

	@Override
	public void deleteUser(List<String> idListStr) throws Exception {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement ps=null;
		List<Integer> idList=new ArrayList<Integer>();
		try {
			for (String idString: idListStr) {
			idList.add(Integer.parseInt(idString));
		}
			conn=DBUtils.getInstance().getConnection();
		for(int id:idList) {
			String sql="delete from users where id = ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		}finally {
			DBUtils.getInstance().close(null, ps, null, conn);
		}
	}
	public List<Users> getUser() throws Exception {
		List<Users> listUsers=new ArrayList<Users>();
		Users users=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DBUtils.getInstance().getConnection();
			String sql="select * from users order by id";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery(sql);
			while (rs.next()) {
				int id=rs.getInt("id");
				String username=rs.getString("username");
				String pass=rs.getString("pass");
				String email=rs.getString("email");
				Date birthday=rs.getDate("birthday");
				users=new Users(id, username, pass, email, birthday);
				listUsers.add(users);
			}
			return listUsers;
		} finally {
			// TODO: handle finally clause
			DBUtils.getInstance().close(rs, ps, null, conn);
		}
	}
	
	public void defSchemaCreate() throws Exception {
		Connection conn=DBUtils.getInstance().getConnection();
		//傳送sql語法
		String sql="create schema testuser";
		Statement st=conn.createStatement();
		st.addBatch("create schema testuser");
		st.addBatch("use testuser");
		//傳送要新增的值
		//ps.setString(1, x);
		
//		ps.executeUpdate();
	}
	public boolean login() throws Exception {
		Connection conn=null;
		try {
			conn=DBUtils.getInstance().getConnection();
		} catch (MySQLNonTransientConnectionException e) {
			DBUtils.getInstance().close(null, null, null, conn);
			 return false;
		}
//		catch (MySQLSyntaxErrorException e) {}
		DBUtils.getInstance().close(null, null, null, conn);
		return true;
	}
}
