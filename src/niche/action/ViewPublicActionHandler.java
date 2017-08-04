package niche.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import niche.bean.Photo;
import niche.bean.PhotoTag;
import niche.bean.User;
import niche.service.PhotoService;
import niche.service.TagsService;
import niche.service.UserService;

public class ViewPublicActionHandler implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("isprivate", false);
		request.setAttribute("ispublic", true);
		request.setAttribute("photos", PhotoService.getAllPublicPhotos());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	
	public static void initializeDatabase () {
		PhotoTag tag1 = new PhotoTag();
		tag1.setTag("TAG1");
		PhotoTag tag2 = new PhotoTag();
		tag2.setTag("TAG2");
		PhotoTag tag3 = new PhotoTag();
		tag3.setTag("TAG3");
		
		Set <PhotoTag> tags = new HashSet <PhotoTag> ();
		tags.add(tag1);
		tags.add(tag2);
		tags.add(tag3);
		
		TagsService.addPhotoTag(tag1);
		TagsService.addPhotoTag(tag2);
		TagsService.addPhotoTag(tag3);
		
		
		for(int i = 0; i < 5; i++) {
			User user = new User();
			user.setUsername("user"+ (i+1));
			user.setPassword("user" + (i+1));
			user.setDescription("This is user no. " + (i+1));
			
			UserService.addUser(user);
			
			for(int j = 0; j < 10; j++) {
				Photo photo = new Photo();
				photo.setPath("static/res/dummy/300.png");
				photo.setTitle("Public Photo ID " + (j+1)*(i+1));
				
				if(i != 0) {
					Set <User> users = new HashSet <User> ();
					users.add(UserService.getUser(i));
					photo.setHasAccess(users);
				} 
				
				if(j % 2 == 0)
					photo.setVisible(true);
				else
					photo.setVisible(false);
				
				photo.setUser(user);
				photo.setDescription("This is public photo no. " + (j+1)*(i+1));
				
				photo.setTags(tags);
				PhotoService.addPhoto(photo);
				
			}
			
		}
	}
	
	public static void main (String [] args) {
		initializeDatabase();
	}

}
