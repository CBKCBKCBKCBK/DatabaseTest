package tw.com.pcschool.dao;

import java.util.List;

import tw.com.pcschool.config.DBUtils;
import tw.com.pcschool.domin.Users;
/**
 * 
 * @author cbk
 *
 */
public interface UserDao {
	/**
	 * 新增使用者
	 * @throws Exception
	 */
	public abstract void addUser(Users users) throws Exception;
	public abstract List<Users> readUser(String id,String data) throws Exception;
	public abstract void updateUser(List<Users> users) throws Exception;
	public abstract void deleteUser(List<String> theId) throws Exception;
	public abstract List<Users> loadUser(List<String> id) throws Exception;
	public abstract List<Users> getUser() throws Exception;
	public abstract boolean login() throws Exception;
}
