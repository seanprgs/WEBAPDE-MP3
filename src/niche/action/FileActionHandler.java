package niche.action;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileActionHandler implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getPathInfo().substring(1);
		String filename = URLDecoder.decode(url, "UTF-8");
		
		File folder = PostActionHandler.FOLDER;
		File file = new File(folder.getPath(), filename);
		response.setHeader("Content-Type", request.getServletContext().getMimeType(filename));
		response.setHeader("Content-Length", String.valueOf(file.length()));
		Files.copy(file.toPath(), response.getOutputStream());
	}

}
