package niche.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import niche.service.PhotoService;

public class LogoutActionHandler implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		for(Cookie c: cookies) {
			if(c.getName().equals("userID")) {
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		
		
		request.getSession().setAttribute("sessionuser", null);
		request.getSession(false);
		
		request.setAttribute("photos", PhotoService.getAllPublicPhotos());
		request.setAttribute("ispublic", true);
		request.setAttribute("isprivate", false);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}
