package demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Board {
	private long id;
	private String title;
	private String content;
	private String name;
	private Date writeDate;
}
