package demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Post {
	private int id;
	private String title;
	private String content;
	private String writer;
	private Date writeDate;
}
