package demo.service;

import demo.model.Board;
import demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Override
    public Board find(long id) {
        return boardRepository.find(id);
    }

    @Override
    public List<Board> findPage() {
        return boardRepository.findPage();
    }

    @Override
    public void write(Board board) {
        boardRepository.write(board);
    }

    @Override
    public void modify(Board board) {
        boardRepository.modify(board);
    }

    @Override
    public void delete(long id) {
        boardRepository.delete(id);
    }

}
