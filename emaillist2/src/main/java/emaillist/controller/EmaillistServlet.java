package emaillist.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emaillist.dao.EmaillistDao;

public class EmaillistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("a");
		// 요청을 제어 - Controller 
		if("".equals(action)) {
			
		} else if("".equals(action)) {
			
		} else {  
			// Default action (emaillist2/el)
			// 리스트를 보여줌 - Dao를 생성해서 하면 됨 
			new EmaillistDao().findAll();
			
			
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
