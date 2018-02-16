package pl.zaprogramuj.spring.boot.webapp.webapp.repository.impl;

import static org.junit.Assert.assertEquals;

import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import pl.zaprogramuj.spring.boot.webapp.dao.PostDao;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Post;
import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationDaoTest;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(value = { ApplicationContextConfigurationDaoTest.class })
public class PostDaoImplTest
{

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PostDao postDao;
	
	@Test
	public void shoulnReturnPostFromEntityManager()
	{
		Post post = new Post();
		post.setTitle("titlePost");
		postDao.addPost(post);
		
		Query query = entityManager.getEntityManager().createQuery("SELECT p from Post p");
		
		assertEquals(1, query.getResultList().size());
	}
	
	@Test
	public void shouldReturnPost()
	{
		Post post = new Post();
		post.setTitle("title");		
		entityManager.persist(post);
		Post daoPost = postDao.findByTitle("title");
		
		assertEquals("title", daoPost.getTitle());
	}
	
	@Test
	public void shouldReturnPostById()
	{		
		Post post = new Post();
		
		entityManager.persist(post);
		
		assertEquals(post.getId(), postDao.findById(post.getId()).getId());
	}
	
	@Test
	public void shouldReturnListPost(){
		Post post1 = new Post();
		Post post2 = new Post();
		
		entityManager.persist(post1);
		entityManager.persist(post2);
		
		assertEquals(2, postDao.findAllPosts().size());
	}
	

	@Test
	public void shouldReturnPostByTitle()
	{
		Post post = new Post();
		post.setTitle("title");
		
		entityManager.persist(post);
		
		assertEquals(post, postDao.findByTitle("title"));
	}
	
	@Test
	public void shouldReturnPostByUrlAddress()
	{
		Post post = new Post();
		post.setUrlAddress("urlAddress");
		
		entityManager.persist(post);
		
		assertEquals(post, postDao.findByUrlAddress("urlAddress"));
	}	
}
