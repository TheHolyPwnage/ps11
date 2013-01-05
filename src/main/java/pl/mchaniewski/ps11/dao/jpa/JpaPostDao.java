package pl.mchaniewski.ps11.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import pl.mchaniewski.ps11.dao.PostDao;
import pl.mchaniewski.ps11.entity.Post;

@Repository("postDao")
public class JpaPostDao extends JpaGenericDao<Post, Long> implements PostDao {

	public List<Post> getByCategoryId(Long categoryId) {
		TypedQuery<Post> query = entityManager.createNamedQuery(
				"Post.getByCategoryId", Post.class);
		query.setParameter("categoryId", categoryId);

		return query.getResultList();
	}

	public List<Post> getByAuthor(String author) {
		TypedQuery<Post> query = entityManager.createNamedQuery(
				"Post.getByAuthor", Post.class);
		query.setParameter("author", author);

		return query.getResultList();
	}

	public List<Post> getAll() {
		TypedQuery<Post> query = entityManager.createNamedQuery("Post.getAll",
				Post.class);

		return query.getResultList();
	}

}
