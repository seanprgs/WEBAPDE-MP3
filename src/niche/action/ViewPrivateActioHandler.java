package niche.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import niche.bean.User;
import niche.service.PhotoService;

public class ViewPrivateActioHandler implements ActionHandler 
{
	private static int photoCounter = 0;
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	 	User user = (User) request.getSession().getAttribute("sessionuser");
	 	List<Photo> photos = PhotoService.getPrivatePhotosOfUser(user.getUserid(), photoCounter, 15);
		photoCounter += photos.size();
	 	
		request.setAttribute("isprivate", true);
	 	request.setAttribute("ispublic", false);
	 	request.setAttribute("photos", photos);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
