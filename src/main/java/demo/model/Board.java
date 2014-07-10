package demo.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Board {
    private int id;
    private String keyName;
    private String name;
}
