package niche.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import niche.bean.Photo;
import niche.bean.User;
import niche.service.PhotoService;

public class PhotoActionHandler implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("photo"));
		
		Photo photo = PhotoService.getPhoto(id);
		User user = (User) request.getSession().getAttribute("sessionuser");
		
		
		if(photo.isVisible()) {
			request.setAttribute("photo", PhotoService.getPhoto(id));
			request.getRequestDispatcher("photo.jsp").forward(request, response);
		} else {
			if(user == null) {
				request.setAttribute("error", "restricted-access-error");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else if(photo.getUser().getUserid() == user.getUserid()) {
				request.setAttribute("photo", PhotoService.getPhoto(id));
				request.getRequestDispatcher("photo.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "restricted-access-error");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
		
		
		
	}

}
