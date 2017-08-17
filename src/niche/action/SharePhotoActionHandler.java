package niche.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import niche.bean.User;
import niche.service.PhotoService;
import niche.service.UserService;

public class SharePhotoActionHandler implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		User u = UserService.getUserByName(request.getParameter("user"));
		int photoid = Integer.parseInt(request.getParameter("photoid"));
		//u
		if(u != null) {
			PhotoService.sharePhoto(photoid, u);
		}
		
		request.getRequestDispatcher("photo.jsp").forward(request, response);
	}

}
