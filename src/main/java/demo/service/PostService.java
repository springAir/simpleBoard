package demo.service;

import demo.model.Post;
import demo.model.PostContainer;

public interface PostService {
	public Post getPost(int id);
	public PostContainer getPostContainer(String boardKeyName, int page);
	public void write(String boardKeyName, Post post);
	public void modify(String boardKeyName, Post post);
	public void delete(int id);
}
