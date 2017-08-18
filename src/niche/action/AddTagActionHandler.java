package niche.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import niche.bean.Photo;
import niche.bean.PhotoTag;
import niche.service.PhotoService;
import niche.service.TagsService;

public class AddTagActionHandler implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get tag to be added
		String tag = request.getParameter("tag");
		String photoID = request.getParameter("photoid");
		Photo photo = PhotoService.getPhoto(Integer.parseInt(photoID));
		System.out.println(photoID);
		System.out.println(photo.toString());
		
		List<PhotoTag> dbTags = TagsService.getAllPhotoTags();
		//filter existing db tags
		boolean foundDB = false;
		for(int i = 0; i < dbTags.size(); i++) {
			if(dbTags.get(i).getTag().equalsIgnoreCase(tag)) {
				photo.addPhotoTag(dbTags.get(i));
				foundDB = true;
			}
		}
		
		if(!foundDB) {
			PhotoTag pt = new PhotoTag();
			pt.setTag(tag);
			photo.addPhotoTag(pt);
		}
		
		System.out.println("\n"+ photo.toString());
		PhotoService.updatePhoto(photo.getPhotoid(), photo);
		
		request.setAttribute("photo", photo);
		request.getRequestDispatcher("photo.jsp").forward(request, response);
	}

}
