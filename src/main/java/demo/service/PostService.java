package demo.service;

import demo.model.Post;
import demo.model.PostContainer;

public interface PostService {
	public Post getPost(int id);
	public PostContainer getPostContainer(String boardKeyName, int page);
	public void write(Post post);
	public void modify(Post post);
	public void delete(int id);
}
