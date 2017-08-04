package niche.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import niche.bean.User;
import niche.service.PhotoService;
import niche.service.UserService;

public class LoginActionHandler implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = UserService.findUser(username, password);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("sessionuser", user);
			
			String rm = request.getParameter("remember");
			if(rm != null) {
				Cookie usernameCookie = new Cookie("userID", String.valueOf(user.getUserid()));
				usernameCookie.setMaxAge(60*60*24*21);
				response.addCookie(usernameCookie);
			}
		}

	 	request.setAttribute("photos", PhotoService.getAllPublicPhotos());
		request.setAttribute("isprivate", false);
	 	request.setAttribute("ispublic", true);
		
		dispatcher.forward(request, response);
	}

}
