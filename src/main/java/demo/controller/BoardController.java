package demo.controller;

import demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @RequestMapping("/board")
    public String getBoardList(Model model){
        model.addAttribute("boards", boardService.getList());
        return "/board/boardList";
    }
}
