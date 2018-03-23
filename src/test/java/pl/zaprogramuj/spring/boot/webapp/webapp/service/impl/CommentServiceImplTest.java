package pl.zaprogramuj.spring.boot.webapp.webapp.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import pl.zaprogramuj.spring.boot.webapp.dao.CommentDao;
import pl.zaprogramuj.spring.boot.webapp.dao.PostDao;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Comment;
import pl.zaprogramuj.spring.boot.webapp.excepotion.comment.CommentNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.excepotion.post.PostNotFoundException;
import pl.zaprogramuj.spring.boot.webapp.service.CommentService;
import pl.zaprogramuj.spring.boot.webapp.service.PostService;
import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationServiceTest;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfigurationServiceTest.class })
public class CommentServiceImplTest {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CommentDao mockCommentDao;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostDao mockPostDao;
	
	@Test(expected = PostNotFoundException.class)
	public void shouldThrowExceptionIfNotExistsPostWithId() throws PostNotFoundException
	{
		when(mockPostDao.findById(1)).thenReturn(null);
		
		commentService.addCommentToPost(Mockito.mock(Comment.class), 1l);
	}
	
	@Test
	public void shouldReturnCommentById() throws CommentNotFoundException
	{
		when(mockCommentDao.findById(1)).thenReturn(Mockito.mock(Comment.class));
		
		assertNotNull(commentService.getCommentById(1));
	}
	
	@Test(expected = CommentNotFoundException.class)
	public void shouldThrowExceptionIfCommentIsNotExists() throws CommentNotFoundException
	{
		when(mockCommentDao.findById(1)).thenReturn(null);
		
		commentService.getCommentById(1);
	}
}
