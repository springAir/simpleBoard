package demo.service;

import demo.model.Board;
import demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardRepository boardRepository;

    @Override
    public Board get(String boardKeyName) {
        return boardRepository.get(boardKeyName);
    }

}
