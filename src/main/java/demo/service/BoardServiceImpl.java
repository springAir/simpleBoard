package demo.service;

import demo.model.Post;
import demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    PostRepository postRepository;

    @Override
    public Post find(long id) {
        return postRepository.find(id);
    }

    @Override
    public List<Post> findPage() {
        return postRepository.findPage();
    }

    @Override
    public void write(Post post) {
        postRepository.add(post);
    }

    @Override
    public void modify(Post post) {
        postRepository.modify(post);
    }

    @Override
    public void delete(long id) {
        postRepository.delete(id);
    }

}
