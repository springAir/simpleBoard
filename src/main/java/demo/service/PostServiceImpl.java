package demo.service;

import demo.model.Board;
import demo.model.Post;
import demo.model.PostContainer;
import demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    BoardService boardService;

    @Override
    public Post getPost(int id) {
        return postRepository.find(id);
    }

    @Override
    public PostContainer getPostContainer(String boardKeyName, int page) {
        int boardId = this.getBoardId(boardKeyName);

        //TODO 페이징 처리.
        PostContainer postContainer = new PostContainer();
        postContainer.setPostList(postRepository.findPage());
        postContainer.setCurrentPageNumber(page);
        postContainer.setTotoalPageNumber(100);

        return postContainer;
    }


    @Override
    public void write(String boardKeyName, Post post) {
        int boardId = this.getBoardId(boardKeyName);
        post.setBoardId(boardId);
        postRepository.add(post);

    }

    @Override
    public void modify(String boardKeyName, Post post) {
        int boardId = this.getBoardId(boardKeyName);
        post.setBoardId(boardId);
        postRepository.modify(post);
    }

    @Override
    public void delete(int id) {
        postRepository.delete(id);
    }

    //Board Service 로 이동 필요.
    private int getBoardId(String boardKeyName){
        Board board = boardService.get(boardKeyName);
        int boardId = board.getId();

        return boardId;
    }
}
