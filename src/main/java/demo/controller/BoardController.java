package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/board/")
	public String viewBoard(){
		return "";
	}

    @RequestMapping("/board/pages/{pageNumber}")
    public String viewBoardPage(){
        return "";
    }

}
