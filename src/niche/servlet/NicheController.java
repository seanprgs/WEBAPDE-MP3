package niche.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import niche.action.AboutActionHandler;
import niche.action.ActionHandler;
import niche.action.AddTagActionHandler;
import niche.action.LoginActionHandler;
import niche.action.LogoutActionHandler;
import niche.action.MoreActionHandler;
import niche.action.PhotoActionHandler;
import niche.action.PostActionHandler;
import niche.action.ProfileActionHandler;
import niche.action.RegisterActionHandler;
import niche.action.SearchActionHandler;
import niche.action.SharePhotoActionHandler;
import niche.action.ViewActionHandler;
import niche.action.WelcomeActionHandler;

/**
 * Servlet implementation class Controller
 */

class URLPatterns {
	public final static String LOGIN = "/login";
	public final static String LOGOUT = "/logout";
	public final static String REGISTER = "/register";
	public final static String POST = "/post";
	public final static String VIEW = "/view";
	public final static String ABOUT = "/about";
	public final static String PROFILE = "/profile";
	public final static String PHOTO = "/photo";
	public final static String WELCOME = "/welcome";
	public static final String SEARCH = "/search";
	public final static String ADDTAG = "/addtag";
	public final static String SHAREPHOTO ="/sharephoto";
	public final static String FILE = "/files/*";
	public final static String MORE = "/more";
	
}

@WebServlet(urlPatterns = {	URLPatterns.LOGIN, 
							URLPatterns.LOGOUT, 
							URLPatterns.REGISTER, 
							URLPatterns.POST, 
							URLPatterns.VIEW, 
							URLPatterns.ABOUT, 
							URLPatterns.PROFILE,
							URLPatterns.PHOTO,
							URLPatterns.WELCOME,
							URLPatterns.SEARCH,
							URLPatterns.ADDTAG,
							URLPatterns.SHAREPHOTO,
							URLPatterns.FILE,
							URLPatterns.MORE})

@MultipartConfig(location="D:\\webapps\\images")
public class NicheController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap <String, ActionHandler> actions;

    public NicheController() {
        super();
        actions = new HashMap <String, ActionHandler> ();
        
        actions.put(URLPatterns.PHOTO,  new PhotoActionHandler());
        actions.put(URLPatterns.LOGIN, new LoginActionHandler());
        actions.put(URLPatterns.LOGOUT, new LogoutActionHandler());
        actions.put(URLPatterns.REGISTER, new RegisterActionHandler());
        actions.put(URLPatterns.POST, new PostActionHandler());
        actions.put(URLPatterns.VIEW, new ViewActionHandler());
        actions.put(URLPatterns.ABOUT, new AboutActionHandler());
        actions.put(URLPatterns.PROFILE, new ProfileActionHandler());
        actions.put(URLPatterns.WELCOME, new WelcomeActionHandler());
        actions.put(URLPatterns.SEARCH, new SearchActionHandler());
        actions.put(URLPatterns.ADDTAG, new AddTagActionHandler());
        actions.put(URLPatterns.SHAREPHOTO, new SharePhotoActionHandler());
        actions.put(URLPatterns.MORE, new MoreActionHandler());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actions.get(request.getServletPath()).execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actions.get(request.getServletPath()).execute(request, response);
	}
}
