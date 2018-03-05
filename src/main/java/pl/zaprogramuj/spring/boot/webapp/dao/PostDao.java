package pl.zaprogramuj.spring.boot.webapp.dao;

import java.util.List;

import pl.zaprogramuj.spring.boot.webapp.domain.post.Post;

public interface PostDao
{
	public abstract void addPost(Post post);
	public abstract Post findByTitle(String title);
	public abstract Post findByUrlAddress(String urlAddress);
	public abstract Post findById(long id);
	public abstract List<Post> findAllPosts();
}
