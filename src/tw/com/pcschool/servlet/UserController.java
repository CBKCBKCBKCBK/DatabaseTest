package tw.com.pcschool.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.pcschool.dao.UserDao;
import tw.com.pcschool.daoimpl.UserDaoImpl;
import tw.com.pcschool.domin.Users;

/**
 * Servlet implementation class UserController
 */
/**
 * 
 * @author cbk
 *
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {

	private final String loginUserName = "databasetest";
	private final String loginPassword = "databasetest";

	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String command = request.getParameter("command");
			if ("修改".equals(request.getParameter("submitfix"))) {
				command = "LOAD";
			} else if ("刪除".equals(request.getParameter("submitdel"))) {
				command = "CONFIRM";
			}
			if (command == null) {
				command = "list";
			}
			switch (command) {
			case "ADD":
				addUser(request, response);
				break;
			case "READ":
				readUser(request, response);
				break;
			case "UPDATE":
				updateUser(request, response);
				break;
			case "DELETE":
				deleteUser(request, response);
				break;
			case "LOAD":
				loadUser(request, response);
				break;
			case "CONFIRM":
				confirmDelete(request, response);
				break;
			case "INDEXLOGIN":
				indexlogin(request, response);
				break;
			case "LOGIN":
				login(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void confirmDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 做跟loadUser一樣的事 導到deleteuser做確認
		List<String> idList = new ArrayList<String>();
		try {
			idList = Arrays.asList(request.getParameterValues("userid"));
		} catch (NullPointerException e) {
			request.getRequestDispatcher("WEB-INF/error/uncheckillegal.jsp").forward(request, response);
		}
		// 取得student資料
		List<Users> users = userDao.loadUser(idList);
		// 接收客戶端請求的資料
		request.setAttribute("userlist", users);
		// 客戶端的請求，用調派員指定顯示的位置
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/database/features/deleteuser.jsp");
		// 發送出去
		dispatcher.forward(request, response);

	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<String> idList = new ArrayList<String>();
		try {
			idList = Arrays.asList(request.getParameterValues("userid"));
		} catch (NullPointerException e) {
			request.getRequestDispatcher("WEB-INF/error/uncheckillegal.jsp").forward(request, response);
		}
//		String  theId=request.getParameter("userid");
		userDao.deleteUser(idList);
		listUser(request, response);
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<String> idListStr = Arrays.asList(request.getParameterValues("userid"));
		List<Integer> idList = new ArrayList<Integer>();
		for (String idString : idListStr) {
			idList.add(Integer.parseInt(idString));
		}
		List<String> usernameList = Arrays.asList(request.getParameterValues("username"));
		List<String> passList = Arrays.asList(request.getParameterValues("pass"));
		List<String> emailList = Arrays.asList(request.getParameterValues("email"));
		List<String> birthdayList = Arrays.asList(request.getParameterValues("birthday"));
		List<Users> userList = new ArrayList<Users>();
		Users users = null;
		for (int i = 0; i < idList.size(); i++) {
			int id = idList.get(i);
			String username = usernameList.get(i).trim();
			String pass = passList.get(i).trim();
			String email = emailList.get(i).trim();
			String birthdaystr = birthdayList.get(i).trim();
			if (username.length() < 1 || pass.length() < 1 || email.length() < 1 || birthdaystr.length() < 1) {
				request.getRequestDispatcher("WEB-INF/error/updatenotkeyin.jsp").forward(request, response);
				throw new Exception();
			}
			Date birthday = null;
			try {
				birthday = Date.valueOf(birthdaystr);
			} catch (IllegalArgumentException e) {
				request.getRequestDispatcher("WEB-INF/error/updateillegal.jsp").forward(request, response);
			}
			users = new Users(id, username, pass, email, birthday);
			userList.add(users);
		}
		userDao.updateUser(userList);
		listUser(request, response);
	}

	private void loadUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 從表單中讀取要顯示的ID
//		String id = request.getParameter("userid");
		List<String> idList = new ArrayList<String>();
		try {
			idList = Arrays.asList(request.getParameterValues("userid"));
		} catch (NullPointerException e) {
			request.getRequestDispatcher("WEB-INF/error/uncheckillegal.jsp").forward(request, response);
		}
		// 取得student資料
		List<Users> users = userDao.loadUser(idList);
		// 接收客戶端請求的資料
		request.setAttribute("userlist", users);
		// 客戶端的請求，用調派員指定顯示的位置
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/database/features/updateuser.jsp");
		// 發送出去
		dispatcher.forward(request, response);
	}

	private void readUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idSearch = request.getParameter("idsearch");
		String dataSearch = request.getParameter("datasearch");
		List<Users> users = userDao.readUser(idSearch, dataSearch);
		request.setAttribute("userlist", users);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/database/features/searchuser.jsp");
		dispatcher.forward(request, response);
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username").trim();
		String pass = request.getParameter("pass").trim();
		String email = request.getParameter("email").trim();
		String birthdayStr = request.getParameter("birthday").trim();

		if (username.length() < 1 || pass.length() < 1 || email.length() < 1 || birthdayStr.length() < 1) {
			request.getRequestDispatcher("WEB-INF/error/addnotkeyin.jsp").forward(request, response);
		}
		Date birthday = null;
		try {
			birthday = Date.valueOf(birthdayStr);
		} catch (IllegalArgumentException e) {
			request.getRequestDispatcher("WEB-INF/error/adddateillegal.jsp").forward(request, response);
		}
		Users users = new Users(username, pass, email, birthday);
		userDao.addUser(users);
		listUser(request, response);
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Users> users = userDao.getUser();
		request.setAttribute("userlist", users);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/database/features/searchuser.jsp");
		dispatcher.forward(request, response);
	}

	private void indexlogin(HttpServletRequest request, HttpServletResponse response) {
		String user = request.getParameter("name");
		String pass = request.getParameter("pass");
		try (PrintWriter out = response.getWriter()) {
			if (user.equals(loginUserName) || pass.equals(loginPassword)) {
				out.print("pass");
			} else {
				out.print("fail");
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = request.getParameter("name");
		String pass = request.getParameter("pass");
		request.getRequestDispatcher("WEB-INF/database/mainframe.jsp").forward(request, response);
//		String dbSchema=request.getParameter("dbschema");
//		System.out.println("dbUser="+dbUser);
//		System.out.println("dbPass="+dbPass);
//		System.out.println("dbSchema="+dbSchema);
		if (user.equals(loginUserName) || pass.equals(loginPassword)) {
			if (userDao.login()) {
				try {
					request.getRequestDispatcher("WEB-INF/database/mainframe.jsp").forward(request, response);
				} catch (IOException | ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					request.getRequestDispatcher("/WEB-INF/error/dbdatanotfound.jsp").forward(request, response);
				} catch (IOException | ServletException e) {
					e.printStackTrace();
				}
			}
		} else {
			request.getRequestDispatcher("WEB-INF/error/loginerror.jsp").forward(request, response);
		}
	}
}
