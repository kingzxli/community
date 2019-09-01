package life.majiang.community.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import life.majiang.community.dto.FileDTO;

@Controller
public class FileController {
	
	@RequestMapping("/file/upload")
	@ResponseBody
	public FileDTO upload(HttpServletRequest request) {
		MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
		MultipartFile file=multiRequest.getFile("editormd-image-file");
//		try {
//			ucloudProvider.upload(file.getInputStream(),file.getContentType(),file.getOriginalFilename());
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
		
		FileDTO fileDTO=new FileDTO();
		fileDTO.setSuccess(1);
		fileDTO.setUrl("/images/lf.jpg");
		return fileDTO;
		
	}

}