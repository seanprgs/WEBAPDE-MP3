package niche.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import niche.service.PhotoService;
import niche.service.UserService;

public class WelcomeActionHandler implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for(Cookie c: cookies) {
				if(c.getName().equals("userID")) {
					request.getSession().setAttribute("sessionuser", UserService.getUser(Integer.parseInt(c.getValue())));
				}
			}
		}
		
		request.setAttribute("isprivate", false);
		request.setAttribute("ispublic", true);
		request.setAttribute("photos", PhotoService.getAllPublicPhotos());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
