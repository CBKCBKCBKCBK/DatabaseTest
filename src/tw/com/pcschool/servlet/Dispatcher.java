package tw.com.pcschool.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.pcschool.config.DBUtils;

/**
 * Servlet implementation class Dispatcher
 */

/**
 * 
 * @author cbk
 *
 */
@WebServlet("/Dispatcher")
public class Dispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dispatcher() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getParameter("a");
		if (url==null) {
			request.getRequestDispatcher("WEB-INF/database/mainframe.jsp")
			.forward(request, response);
		}else {
			switch (url) {
			case "add":
				request.getRequestDispatcher("WEB-INF/database/features/adduser.jsp")
				.forward(request, response);
				break;
			case "find":
				request.getRequestDispatcher("WEB-INF/database/features/searchuser.jsp")
				.forward(request, response);
				break;
			case "fix":
				request.getRequestDispatcher("WEB-INF/database/features/updateuser.jsp")
				.forward(request, response);
				break;
			case "del":
				request.getRequestDispatcher("WEB-INF/database/features/deleteuser.jsp")
				.forward(request, response);
				break;
			case "list":
				request.getRequestDispatcher("UserController")
				.forward(request, response);
				break;
			case "login":
				request.getRequestDispatcher("UserController?command=LOGIN")
				.forward(request, response);
				break;

			default:
				request.getRequestDispatcher("WEB-INF/database/mainframe.jsp")
				.forward(request, response);
				break;
			}
		}
		
	}

}
