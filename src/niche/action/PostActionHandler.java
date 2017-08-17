package niche.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import niche.bean.Photo;
import niche.bean.PhotoTag;
import niche.bean.User;
import niche.service.PhotoService;
import niche.service.TagsService;

public class PostActionHandler implements ActionHandler {
	
	public static File FOLDER = new File("D://nicheIMGs");
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Retrieve photo details
		String uploadFile = request.getParameter("file");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String num = request.getParameter("tagNum");
		//String tag0 = request.getParameter("tag0");
		
		int numOfTags = 0;
		if(num!=null)
			numOfTags = Integer.parseInt(request.getParameter("tagNum"));
		Set <PhotoTag> photoTags = new HashSet <PhotoTag> ();	//final
		
		ArrayList<String> filtered = new ArrayList<String>();	//filtered
		List<PhotoTag> dbTags = TagsService.getAllPhotoTags();	//from db
		
		//get all tags to be added
		ArrayList<String> tags = new ArrayList<String>();
		for(int i = 0; i < numOfTags; i++)
		{
			tags.add(request.getParameter("tag"+i));
		}
		
		//filter exsiting db tags
		for(int i = 0; i < dbTags.size(); i++) {
			if(tags.contains(dbTags.get(i).getTag())) {
				photoTags.add(dbTags.get(i));
				//remove added db tags
				tags.remove(dbTags.get(i).getTag());
			}
		}
		
		//filter new tags
		for(int i = 0; i < tags.size(); i++) {
			if(!filtered.contains(tags.get(i)))
				filtered.add(tags.get(i));
		}
		
		for(int i = 0; i < filtered.size(); i++) {
			PhotoTag pt = new PhotoTag();
			pt.setTag(filtered.get(i));
			photoTags.add(pt);
			System.out.println(pt.toString());
		}
		
		//Retrieve file
		Part part = request.getPart("file");
		
		//Create a filename for the image file to save
		String fileExtension = null;
		switch(part.getContentType())
		{
			case "image/jpeg":
				fileExtension = ".jpeg";
				break;
			case "image/tiff":
				fileExtension = ".tiff";
				break;
			case "image/png":
				fileExtension = ".png";
				break;
			default:
				System.out.println(" Invalid file type.");
		}
		String filename = System.currentTimeMillis() + "-" + title + fileExtension;
		
		System.out.println(uploadFile);
		System.out.println(title);
		System.out.println(description);
		System.out.println(num);
		//System.out.println(tag0);
		
		//Create filename to be saved to FOLDER
		File file = new File(FOLDER, filename);
		
		//Save the file
		InputStream fileInputStream = part.getInputStream();
		Files.copy(fileInputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		fileInputStream.close();
		
		//Save photo to db
		Photo newPhoto = new Photo();
		newPhoto.setPath(file.getPath().replace("\\", "/"));
		newPhoto.setDescription(description);
		newPhoto.setTags(photoTags);
		newPhoto.setTitle(title);
		
		boolean isVisible = true;
		if(request.getParameter("visible").equalsIgnoreCase("private"))
			isVisible = false;
		newPhoto.setVisible(isVisible);
		
		User u = (User)request.getSession().getAttribute("sessionuser");
		newPhoto.setUser(u);
		
		if(PhotoService.addPhoto(newPhoto))
			System.out.println("Photo uploaded successfully");
		else
			System.out.println("Aw.");
		
		request.setAttribute("isprivate", false);
		request.setAttribute("ispublic", true);
		request.setAttribute("photos", PhotoService.getAllPublicPhotos());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
