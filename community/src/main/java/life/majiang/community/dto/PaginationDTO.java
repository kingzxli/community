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
	
	public void setPagination(Integer totalCount,Integer page,Integer size) {
	    this.page=page;
		if(totalCount%size==0) {
			totalPage=totalCount/size;
		}else {
			totalPage=totalCount/size + 1;
		}
		if(page>totalPage) {
			page=totalPage;
		}
		
		pages.add(page);
		for(int i=1;i<=3;i++) {  //1
			if(page-i>0) {
				pages.add(0,page-i);
			}
			if(page+i<totalPage) {
				pages.add(page+i);
			}
		}
		
		
	}
	
	

}
