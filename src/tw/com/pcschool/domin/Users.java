package tw.com.pcschool.domin;

import java.io.Serializable;
import java.sql.Date;
/**
 * 
 * @author cbk
 *
 */
public class Users implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String pass;
	private String email;
	private Date birthday;

	public Users() {
		
	}
	
	public Users(String username, String pass, String email, Date birthday) {
		super();
		this.username = username;
		this.pass = pass;
		this.email = email;
		this.birthday = birthday;
	}
	
	public Users(int id, String username, String pass, String email, Date birthday) {
		super();
		this.id = id;
		this.username = username;
		this.pass = pass;
		this.email = email;
		this.birthday = birthday;
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * 
	 * @param pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 
	 * @return
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * 
	 * @param birthday
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	


}
