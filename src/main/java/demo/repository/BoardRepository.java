package demo.repository;


import demo.model.Board;

public interface BoardRepository {


    Board get(String keyName);

    long add(Board board);
}
