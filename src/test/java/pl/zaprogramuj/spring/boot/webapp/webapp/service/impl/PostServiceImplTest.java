package pl.zaprogramuj.spring.boot.webapp.webapp.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import pl.zaprogramuj.spring.boot.webapp.dao.PostDao;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Post;
import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostException;
import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostExistException;
import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.service.PostService;
import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationServiceTest;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfigurationServiceTest.class })
public class PostServiceImplTest
{
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostDao mockDao;
	
	@Test
	public void shouldReturnPost() throws PostNotFoundException
	{
		Post post = new Post();
		
		when(mockDao.findById(1)).thenReturn(post);
		
		assertEquals(post, postService.getPostById(1));
	}
	
	@Test(expected = PostNotFoundException.class)
	public void shouldThrowExceptionIfPostIsNull() throws PostNotFoundException
	{
		when(mockDao.findById(1)).thenReturn(null);
		
		postService.getPostById(1);
	}
	
	@Test
	public void shouldReturnListOfPost()
	{
		List<Post> posts = new ArrayList<>();
		posts.add(Mockito.mock(Post.class));
		posts.add(Mockito.mock(Post.class));
		
		when(mockDao.findAllPosts()).thenReturn(posts);
		
		assertEquals(posts.size(), postService.getAllPosts().size());
	}
	
	@Test
	public void shouldReturnEmptyListIfNoOnePostExists()
	{
		when(mockDao.findAllPosts()).thenReturn(null);
		
		assertNotNull(postService.getAllPosts());
	}
	
	@Test
	public void shouldReturnPostByUrlAddress() throws PostNotFoundException
	{
		Post postMock = Mockito.mock(Post.class);
		
		when(mockDao.findByUrlAddress("urlAddress")).thenReturn(postMock);
		
		assertNotNull(postService.getPostByUrlAddress("urlAddress"));
	}
	
	@Test(expected = PostNotFoundException.class)
	public void shouldThrowExceptionIfNotExistsPostWithUrlAddress() throws PostNotFoundException
	{
		when(mockDao.findByUrlAddress("urlAddress")).thenReturn(null);
		
		postService.getPostByUrlAddress("urlAddress");
	}
	
	@Test(expected = PostExistException.class)
	public void shouldThrowExceptionIfExistsPostWithSameUrlAddress() throws PostException
	{
		Post postMock = Mockito.mock(Post.class);
		
		when(postMock.getUrlAddress()).thenReturn("urlAddress");
		when(mockDao.findByUrlAddress("urlAddress")).thenReturn(postMock);
		
		postService.addPost(postMock);
	}
	
	@Test
	public void shouldReturnTrueIfExistsPostWithSameUrl()
	{
		when(mockDao.findByUrlAddress("urlAddress")).thenReturn(Mockito.mock(Post.class));
		
		assertTrue(postService.isPostWithUrlAddress("urlAddress"));
	}
}
