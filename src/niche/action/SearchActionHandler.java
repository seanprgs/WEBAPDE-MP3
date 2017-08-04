package niche.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import niche.bean.Photo;
import niche.bean.PhotoTag;
import niche.service.PhotoService;

public class SearchActionHandler implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tag = request.getParameter("tag");
		List <Photo> photos = PhotoService.getAllPublicPhotos();
		
		for(int i = 0; i < photos.size(); i++)
		{
			Iterator <PhotoTag> tags = photos.get(i).getTags().iterator();
			boolean retain = false;
			int a = 0;
			while(tags.hasNext()) {
				PhotoTag ptag = tags.next();
				if(ptag.getTag().equals(tag)) {
					retain = true;
				}
				
				if(!retain)
					a++;
			}
			
			if(!retain) {
				photos.remove(a);
			}
		}
		request.setAttribute("tag", tag);
		request.setAttribute("photos", photos);
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}

}
