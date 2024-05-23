package emaillist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emaillist.dao.EmaillistDao;
import emaillist.vo.EmaillistVo;

public class EmaillistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("a");
		// 요청을 제어 - Controller 
		if("form".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/form.jsp");  //webapp 경로 기준으로 
			rd.forward(request, response);
			
		} else if("add".equals(action)) {
			request.setCharacterEncoding("utf-8");
			String firstName= request.getParameter("fn");
			String lastName= request.getParameter("ln");
			String email= request.getParameter("email");
			
			EmaillistVo vo = new EmaillistVo();
			vo.setFirstName(firstName);
			vo.setLastName(lastName);
			vo.setEmail(email);
			new EmaillistDao().insert(vo);
			
			response.sendRedirect(request.getContextPath() + "/el");
			
		} else {  
			// Default action (emaillist2/el)
			// 리스트(list)를 보여줌 - Dao를 생성해서 하면 됨 
			List<EmaillistVo> list = new EmaillistDao().findAll();
			request.setAttribute("list", list);  // AttributeMap(name, value)
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");  //webapp 경로 기준으로 
			rd.forward(request, response);
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
