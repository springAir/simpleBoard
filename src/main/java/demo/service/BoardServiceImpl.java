package demo.service;

import demo.model.Board;
import demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardRepository boardRepository;

    @Override
    public Board get(String boardKeyName) {
        return boardRepository.find(boardKeyName);
    }

    @Override
    public List<Board> getList(){
        return boardRepository.findList();
    }

}
