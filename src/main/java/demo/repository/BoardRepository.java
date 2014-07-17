package demo.repository;


import demo.model.Board;

import java.util.List;

public interface BoardRepository {


    Board find(String keyName);

    List<Board> findList();

    long add(Board board);
}
