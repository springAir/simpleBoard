package demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class PostContainer {
    private List<Post> postList;
    private Board board;
    private int totalPageNumber;
    private int currentPageNumber;
}
