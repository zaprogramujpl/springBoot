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

import pl.zaprogramuj.spring.boot.webapp.dao.CommentDao;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Comment;
import pl.zaprogramuj.spring.boot.webapp.webapp.configuration.ApplicationContextConfigurationDaoTest;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(value = { ApplicationContextConfigurationDaoTest.class })
public class CommentDaoTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CommentDao commentDao;
	
	@Test
	public void shouldAddCommentToDB()
	{
		Comment comment = new Comment();
		commentDao.saveComment(comment);
		
		Query query = entityManager.getEntityManager().createQuery("from Comment c");
		assertEquals(1, query.getResultList().size());
	}
}
