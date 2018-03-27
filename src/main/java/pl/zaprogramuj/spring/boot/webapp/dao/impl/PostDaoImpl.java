package pl.zaprogramuj.spring.boot.webapp.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import pl.zaprogramuj.spring.boot.webapp.dao.AbstractDao;
import pl.zaprogramuj.spring.boot.webapp.dao.PostDao;
import pl.zaprogramuj.spring.boot.webapp.domain.post.Post;

@Repository
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class PostDaoImpl extends AbstractDao<Long, Post> implements PostDao {

	@Override
	public Post findByTitle(String title) {
		Session session = getEntityMenager().unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Post> criteriaQuery = builder.createQuery(Post.class);
		Root<Post> rootPost = criteriaQuery.from(Post.class);
		criteriaQuery.select(rootPost).where(builder.equal(rootPost.get("title"), title));
		Query<Post> query = session.createQuery(criteriaQuery);

		return query.getResultList().isEmpty() ? null : query.getSingleResult();
	}

	@Override
	public void addPost(Post post) {
		persist(post);
	}

	@Override
	public Post findById(long id) {
		return getByPrimaryKey(id);
	}

	@Override
	public List<Post> findAllPosts() {
		return getEntityMenager().createQuery("from Post p ORDER BY p.creationDate DESC", Post.class).getResultList();
	}

	@Override
	public Post findByUrlAddress(String urlAddress) {
		Session session = getEntityMenager().unwrap(Session.class);
		Query<Post> query = session.createQuery("from Post p LEFT JOIN FETCH p.comments c WHERE p.urlAddress = :urlAddress AND c.parent IS NULL", Post.class);
		query.setParameter("urlAddress", urlAddress);
		return query.getResultList().size() > 0 ? query.getSingleResult() : null;
	}
}
