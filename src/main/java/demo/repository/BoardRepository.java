package demo.repository;

import demo.model.Board;

import java.util.List;

public interface BoardRepository {
    public Board find(long id);
    public List<Board> findPage();
    public long count();
    public long write(Board board);
    public void modify(Board board);
    public void delete(long id);
}
