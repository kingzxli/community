package life.majiang.community;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationTests {

	@Test
	public void contextLoads() {
		List<Integer> pages=new ArrayList<>();
		Integer totalPage=20;
		int page=7;
		pages.add(page);
		for(int i=1;i<=3;i++) {  //1
			if(page-i>0) {
				pages.add(0,page-i);
			}
			if(page+i<totalPage) {
				pages.add(page+i);
			}
		}
		System.out.println(pages);
		
	}

}
