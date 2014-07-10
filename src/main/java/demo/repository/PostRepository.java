package demo.repository;

import demo.model.Post;

import java.util.List;

public interface PostRepository {
    public Post find(long id);
    public List<Post> findPage();
    public int count();
    public int add(Post post);
    public void modify(Post post);
    public void delete(long id);
}
