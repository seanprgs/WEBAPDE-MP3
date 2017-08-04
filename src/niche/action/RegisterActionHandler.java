package niche.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import niche.bean.User;
import niche.service.PhotoService;
import niche.service.UserService;

public class RegisterActionHandler implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String description = request.getParameter("description");
		
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setDescription(description);
		
		if(!UserService.addUser(u)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			request.setAttribute("error", "db-connection-error");
			dispatcher.forward(request, response);
		} else 
			request.setAttribute("isprivate", false);
	 		request.setAttribute("ispublic", true);
	 		request.setAttribute("photos", PhotoService.getAllPublicPhotos());
			request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
