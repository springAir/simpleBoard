package demo.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class BoardContainer {
    private List<Post> postList;
    private long currentPageNumber;
    private long totalPageNumber;
}
