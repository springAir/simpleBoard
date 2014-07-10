package demo.service;

import demo.model.Post;
import demo.model.PostContainer;
import demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public Post getPost(int id) {
        return postRepository.find(id);
    }

    @Override
    public PostContainer getPostContainer(String boardKeyName, int page) {
        return null;
    }


    @Override
    public void write(Post post) {

    }

    @Override
    public void modify(Post post) {

    }

    @Override
    public void delete(int id) {

    }
}
