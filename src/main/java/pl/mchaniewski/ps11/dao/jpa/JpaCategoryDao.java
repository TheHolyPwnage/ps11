package pl.mchaniewski.ps11.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import pl.mchaniewski.ps11.dao.CategoryDao;
import pl.mchaniewski.ps11.entity.Category;

@Repository("categoryDao")
public class JpaCategoryDao extends JpaGenericDao<Category, Long> implements
		CategoryDao {

	public List<Category> getByAuthor(String author) {
		TypedQuery<Category> query = entityManager.createNamedQuery(
				"Category.getByAuthor", Category.class);
		query.setParameter("author", author);

		return query.getResultList();
	}

	public List<Category> getAll() {
		TypedQuery<Category> query = entityManager.createNamedQuery(
				"Category.getAll", Category.class);

		return query.getResultList();
	}

}
