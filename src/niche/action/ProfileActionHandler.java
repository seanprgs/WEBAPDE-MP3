package niche.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import niche.bean.User;
import niche.service.PhotoService;
import niche.service.UserService;

public class ProfileActionHandler implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = UserService.getUser(Integer.parseInt(request.getParameter("user")));
	 	User sessionuser = (User) request.getSession().getAttribute("sessionuser");
		
		if(sessionuser == null) {
			request.setAttribute("photos", PhotoService.getPublicPhotosOfUser(user.getUserid()));
		} else if (sessionuser.getUserid() == user.getUserid()) {
			request.setAttribute("photos", PhotoService.getPhotosOfUser(user.getUserid()));
		} else {
			request.setAttribute("photos", PhotoService.getPublicPhotosOfUser(user.getUserid()));
		}
		
		request.setAttribute("user", user);
		request.getRequestDispatcher("profile.jsp").forward(request, response);
	}
}
