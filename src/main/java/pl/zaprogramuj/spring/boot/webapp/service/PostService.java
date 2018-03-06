package pl.zaprogramuj.spring.boot.webapp.service;

import java.util.List;

import pl.zaprogramuj.spring.boot.webapp.domain.post.Post;
import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostException;
import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostNotFoundException;

public interface PostService
{
	public abstract void addPost(Post post) throws PostException;
	public abstract Post getPostById(long id) throws PostNotFoundException;
	public abstract Post getPostByTitle(String title);
	public abstract Post getPostByUrlAddress(String urlAddress) throws PostNotFoundException;
	public abstract List<Post> getAllPosts();
	public abstract List<Post> getPostsByAuthorId();
	public abstract boolean isPostWithUrlAddress(String urlAddress);
}
