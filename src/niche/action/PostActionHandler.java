package niche.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import niche.bean.PhotoTag;
import niche.service.PhotoService;

public class PostActionHandler implements ActionHandler {
	
	public static File FOLDER = new File("/Users/Sean Paragas/Desktop");
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Retrieve photo details
		String uploadFile = request.getParameter("file");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String num = request.getParameter("tagNum");
		//String tag0 = request.getParameter("tag0");
		
		int numOfTags = Integer.parseInt(request.getParameter("tagNum"));
		Set <PhotoTag> photoTags = new HashSet <PhotoTag> ();
		for(int i = 0; i < numOfTags; i++) {
			PhotoTag pt = new PhotoTag();
			pt.setTag(request.getParameter("tag"+i));
			photoTags.add(pt);
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
		
		//Save tags to db
		
		request.setAttribute("isprivate", false);
		request.setAttribute("ispublic", true);
		request.setAttribute("photos", PhotoService.getAllPublicPhotos());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
