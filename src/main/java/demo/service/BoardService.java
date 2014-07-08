package demo.service;

import demo.model.Post;

import java.util.List;

public interface BoardService {
	public Post find(long id);
	public List<Post> findPage();
	public void write(Post post);
	public void modify(Post post);
	public void delete(long id);
}
