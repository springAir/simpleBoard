package demo.controller;

import demo.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/board/{boardName}")
    public String viewPostPage(@PathVariable String boardName) {
        return "";
    }

    @RequestMapping("/board/{boardName}/{pageNumber}")
    public String viewPostPage(@PathVariable String boardName, @PathVariable int pageNumber, Model model) {
        model.addAttribute(postService.getPostContainer(boardName, pageNumber));
        return "postList";
    }

    @RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
    public String viewPost(@PathVariable int postId, Model model) {
        model.addAttribute("post", postService.getPost(postId));
        return "post";
    }

    @RequestMapping(value = "/post/{postId}", method = RequestMethod.PUT)
    public String modifyPost(@PathVariable int postId, Model model) {
        throw new RuntimeException("TODO");
    }

    @RequestMapping(value = "/post/{postId}", method = RequestMethod.DELETE)
    public String deletePost(@PathVariable int postId) {
        throw new RuntimeException("TODO");
    }

    @RequestMapping(value = "/post/", method = RequestMethod.POST)
    public String writePost(@PathVariable int postId, Model model) {
        model.addAttribute("post", postService.getPost(postId));
        return "post";
    }

}
