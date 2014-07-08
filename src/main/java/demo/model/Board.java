package demo.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Board {
    private long id;
    private String keyName;
    private String name;
}
