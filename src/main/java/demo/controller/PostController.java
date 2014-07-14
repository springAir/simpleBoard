package demo.controller;

import demo.model.Post;
import demo.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class PostController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping({"/board/{boardKeyName}",  "/board/{boardKeyName}/page"})
    public String viewPostPage(@PathVariable String boardKeyName) {
       return "forward:/board/"+ boardKeyName + "/page/1";
    }

    @RequestMapping("/board/{boardKeyName}/page/{pageNumber}")
    public String viewPostPage(@PathVariable String boardKeyName, @PathVariable int pageNumber, Model model) {
        model.addAttribute(postService.getPostContainer(boardKeyName, pageNumber));
        return "post/postList";
    }

    @RequestMapping(value = "/board/{boardKeyName}/post/{postId}", method = RequestMethod.GET)
    public String viewPost(@PathVariable int postId, Model model) {
        Post post = postService.getPost(postId);
        model.addAttribute(post);
        return "post/post";
    }


    @RequestMapping(value = "/board/{boardKeyName}/post/new", method = RequestMethod.GET)
    public String writePost(@PathVariable String boardKeyName, Model model) {
        model.addAttribute("boardKeyName", boardKeyName);
        return "post/newPost";
    }

    @RequestMapping(value = "/board/{boardKeyName}/post/new", method = RequestMethod.POST)
    public String writePost(@PathVariable String boardKeyName, Post post) {
        post.setWriteDate(new Date());
        postService.write(boardKeyName, post);
        //TODO 에러처리
        return "redirect:/board/"+boardKeyName;
    }


    @RequestMapping(value = "/board/{boardKeyName}/post/{postId}/edit", method = RequestMethod.PUT)
    public String modifyPost(@PathVariable int postId, Model model) {
        throw new RuntimeException("TODO");
    }

    @RequestMapping(value = "/board/{boardKeyName}/post/{postId}", method = RequestMethod.DELETE)
    public String deletePost(@PathVariable int postId) {
        throw new RuntimeException("TODO");
    }
}
