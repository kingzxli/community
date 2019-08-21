package life.majiang.community.model;

import lombok.Data;

@Data
public class Quesstion {
	private int id;
	private String title;
	private String description;
	private Long gmtCreate;
	private Long gmtModified;
	private Integer creator;
	private Integer commentCount;
	private Integer viewCount;
	private Integer likeCount;
	private String tag;

}