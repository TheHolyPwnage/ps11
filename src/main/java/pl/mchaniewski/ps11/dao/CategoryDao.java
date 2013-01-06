package pl.mchaniewski.ps11.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pl.mchaniewski.ps11.entity.Category;

public interface CategoryDao extends GenericDao<Category, Integer> {

	@Transactional
	public List<Category> getByAuthor(String author);

	@Transactional
	public List<Category> getAll();
}
