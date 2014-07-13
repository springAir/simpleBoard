package demo.service;

import demo.model.Board;
import demo.model.Post;
import demo.model.PostContainer;
import demo.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImpl implements PostService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final int DEFAULT_PAGE_SIZE = 20;
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
        int startRow = (page - 1) * DEFAULT_PAGE_SIZE;
        int endRow = startRow + DEFAULT_PAGE_SIZE;

        PostContainer postContainer = new PostContainer();
        postContainer.setPostList(postRepository.findPage(boardId, startRow, endRow));
        logger.info("postRepository.findPage(boardId, startRow, endRow) {}", postRepository.findPage(boardId, startRow, endRow));
        postContainer.setCurrentPageNumber(page);
        postContainer.setTotalPageNumber((page + 1) / DEFAULT_PAGE_SIZE);

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
    private int getBoardId(String boardKeyName) {
        Board board = boardService.get(boardKeyName);
        int boardId = board.getId();

        return boardId;
    }
}
