package pl.zaprogramuj.spring.boot.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.zaprogramuj.spring.boot.webapp.dao.PostDao;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Post;
import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostException;
import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostExistException;
import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.service.PostService;

@Service
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class PostServiceImpl implements PostService {
	@Autowired
	private PostDao postDao;

	@Override
	public void addPost(Post post) throws PostException {
		if (post.getUrlAddress() == null || post.getUrlAddress().isEmpty())
			throw new PostException(PostException.EMPTY_OR_NULL_URL_ADDRESS);

		if (isPostWithUrlAddress(post.getUrlAddress()))
			throw new PostExistException("There is already a post with an " + post.getUrlAddress() + " url address.");

		postDao.addPost(post);
	}

	@Override
	public Post getPostById(long id) throws PostNotFoundException {
		Post post = postDao.findById(id);
		if (post == null)
			throw new PostNotFoundException(id);
		return post;
	}

	@Override
	public List<Post> getAllPosts() {
		List<Post> posts = postDao.findAllPosts();
		if (posts == null)
			posts = new ArrayList<>();
		return posts;
	}

	@Override
	public List<Post> getPostsByAuthorId() {
		return null;
	}

	@Override
	public Post getPostByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostByUrlAddress(String urlAddress) throws PostNotFoundException {
		Post post = postDao.findByUrlAddress(urlAddress);

		if (post == null)
			throw new PostNotFoundException(urlAddress);

		return postDao.findByUrlAddress(urlAddress);
	}

	@Override
	public boolean isPostWithUrlAddress(String urlAddress) {
		return postDao.findByUrlAddress(urlAddress) != null;
	}
}
