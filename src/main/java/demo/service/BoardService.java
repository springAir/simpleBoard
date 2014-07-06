package demo.service;

import demo.model.Board;

import java.util.List;

public interface BoardService {
	public Board find(long id);
	public List<Board> findPage();
	public void write(Board board);
	public void modify(Board board);
	public void delete(long id);
}
