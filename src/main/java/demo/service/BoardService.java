package demo.service;

import demo.model.Board;

import java.util.List;

public interface BoardService {
    Board get(String boardKeyName);

    List<Board> getList();
}
