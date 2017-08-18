package niche.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import niche.bean.Photo;
import niche.bean.User;
import niche.service.PhotoService;

public class MoreActionHandler implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <Photo> photos = new ArrayList <Photo>();
		User u = (User) request.getAttribute("sessionuser");
		
		if(u == null)
			photos = PhotoService.getNumberOfPhotosPublic(Integer.parseInt(request.getParameter("page"))*ViewActionHandler.SIZE, ViewActionHandler.SIZE);	
		else 
			photos = PhotoService.getPhotosSeenByUser(u.getUserid(), Integer.parseInt(request.getParameter("page")), ViewActionHandler.SIZE);	
		
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(photos, new TypeToken<List<Photo>>() {}.getType());

		JsonArray array = element.getAsJsonArray();
		response.setContentType("application/json");
		response.getWriter().print(array);
	}

}
