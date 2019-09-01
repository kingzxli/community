package life.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import life.majiang.community.dto.FileDTO;

@Controller
public class FileController {
	
	@RequestMapping("/file/upload")
	@ResponseBody
	public FileDTO upload() {
		FileDTO fileDTO=new FileDTO();
		fileDTO.setSuccess(1);
		fileDTO.setUrl("/images/lf.jpg");
		return fileDTO;
		
	}

}