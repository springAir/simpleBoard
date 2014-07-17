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
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping({"/board/{boardKeyName}", "/board/{boardKeyName}/page"})
    public String viewPostPage(@PathVariable String boardKeyName) {
        return "forward:/board/" + boardKeyName + "/page/1";
    }

    @RequestMapping("/board/{boardKeyName}/page/{pageNumber}")
    public String viewPostPage(@PathVariable String boardKeyName, @PathVariable int pageNumber, Model model) {
        model.addAttribute(postService.getPostContainer(boardKeyName, pageNumber));
        return "post/postList";
    }

    //목록으로 돌아가는 정책 정하기.
    //url을 기준으로?? page/2/postId? 요렇게?? 이거나 저거나 거기서 거기인 듯.
    @RequestMapping(value = "/board/{boardKeyName}/post/{postId}", method = RequestMethod.GET)
    public String viewPost(
            @PathVariable String boardKeyName,
            @PathVariable int postId, Model model,
            @RequestParam(required = false, defaultValue = "1") int pageNumber) {
        Post post = postService.getPost(postId);
        model.addAttribute("post", post);
        model.addAttribute("boardKeyName", boardKeyName);
        model.addAttribute("pageNumber", pageNumber);
        return "post/post";
    }


    @RequestMapping(value = "/board/{boardKeyName}/post/new", method = RequestMethod.GET)
    public String viewWritePost(@PathVariable String boardKeyName, Model model) {
        model.addAttribute("boardKeyName", boardKeyName);
        return "post/newPost";
    }

    @RequestMapping(value = "/board/{boardKeyName}/post", method = RequestMethod.POST)
    public String writePost(@PathVariable String boardKeyName, Post post) {

        post.setWriteDate(new Date());
        postService.write(boardKeyName, post);

        //TODO 에러처리
        return "redirect:/board/" + boardKeyName;
    }


    @RequestMapping(value = "/board/{boardKeyName}/post/{postId}/edit", method = RequestMethod.GET)
    public String viewModifyPost(@PathVariable String boardKeyName, @PathVariable int postId, Model model) {
        model.addAttribute("boardKeyName", boardKeyName);
        model.addAttribute("post", postService.getPost(postId));
        return "post/editPost";
    }

    @RequestMapping(value = "/board/{boardKeyName}/post/{postId}", method = RequestMethod.PUT)
    public String modifyPost(@PathVariable String boardKeyName, Post post, Model model) {
        post.setWriteDate(new Date());
        postService.modify(boardKeyName, post);

        return "redirect:/board/" + boardKeyName;
    }

    @RequestMapping(value = "/board/{boardKeyName}/post/{postId}", method = RequestMethod.DELETE)
    public String deletePost(@PathVariable String boardKeyName, @PathVariable int postId) {
        postService.delete(postId);
        return "redirect:/board/" + boardKeyName;
    }

}
