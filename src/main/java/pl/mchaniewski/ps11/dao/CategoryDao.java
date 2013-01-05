package pl.mchaniewski.ps11.dao;

import java.util.List;

import pl.mchaniewski.ps11.entity.Category;

public interface CategoryDao extends GenericDao<Category, Long> {
	public List<Category> getByAuthor(String author);

	public List<Category> getAll();
}
