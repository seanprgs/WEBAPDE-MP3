package niche.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import niche.bean.User;
import niche.service.PhotoService;

public class ViewPrivateActioHandler implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("isprivate", true);
	 	request.setAttribute("ispublic", false);
	 	User user = (User) request.getSession().getAttribute("sessionuser");
	 	
	 	request.setAttribute("photos", PhotoService.getPrivatePhotosOfUser(user.getUserid()));
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
