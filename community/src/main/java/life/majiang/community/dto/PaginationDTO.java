package life.majiang.community.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class PaginationDTO {
	private List<QuestionDTO> question;
	private Integer page;
	private List<Integer> pages=new ArrayList<>();
	private Integer totalPage;
	
	public void setPagination(Integer totalPage,Integer page) {
	    this.totalPage=totalPage;
	    this.page=page;
	    
			for(int i=3;i>=1;i--) {
				if(page-i>0) {
					pages.add(page-i);
				}
			}
			pages.add(page);
			for(int i=1;i<=3;i++) {
				if(page+i<=totalPage) {
					pages.add(page+i);
				}
			}

	}
	
	

}
